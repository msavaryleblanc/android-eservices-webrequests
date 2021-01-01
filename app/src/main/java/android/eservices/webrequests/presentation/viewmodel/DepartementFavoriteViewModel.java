package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.entity.BookEntity;
import android.eservices.webrequests.data.entity.DepartementEntity;
import android.eservices.webrequests.data.repository.bookdisplay.BookDisplayRepository;
import android.eservices.webrequests.data.repository.bookdisplay.DepartementDisplayRepository;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.DepartementDetailViewModel;
import android.eservices.webrequests.presentation.bookdisplay.favorite.mapper.DepartementEntityToDetailViewModelMapper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class DepartementFavoriteViewModel extends ViewModel {

    private DepartementDisplayRepository departementDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private DepartementEntityToDetailViewModelMapper departementEntityToDetailViewModelMapper;

    public DepartementFavoriteViewModel(DepartementDisplayRepository departementDisplayRepository) {
        this.departementDisplayRepository = departementDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.departementEntityToDetailViewModelMapper = new DepartementEntityToDetailViewModelMapper();
    }

    private MutableLiveData<List<DepartementDetailViewModel>> favorites;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<String>> departementAddedEvent = new MutableLiveData<Event<String>>();
    final MutableLiveData<Event<String>> departementDeletedEvent = new MutableLiveData<Event<String>>();

    public MutableLiveData<Event<String>> getDepartementAddedEvent() {
        return departementAddedEvent;
    }

    public MutableLiveData<Event<String>> getDepartementDeletedEvent() {
        return departementDeletedEvent;
    }

    public MutableLiveData<List<DepartementDetailViewModel>> getFavorites() {
        isDataLoading.setValue(true);
        if (favorites == null) {
            favorites = new MutableLiveData<List<DepartementDetailViewModel>>();
            compositeDisposable.add(departementDisplayRepository.getFavoriteDepartement()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<DepartementEntity>>() {

                        @Override
                        public void onNext(List<DepartementEntity> departementEntityList) {
                            isDataLoading.setValue(false);
                            favorites.setValue(departementEntityToDetailViewModelMapper.map(departementEntityList));
                            System.out.println("BIND FAVORITES");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onComplete() {
                            //Do Nothing
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return favorites;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void addDepartementToFavorite(final String depId) {
        compositeDisposable.add(departementDisplayRepository.addDepartementToFavorites(depId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        departementAddedEvent.setValue(new Event<String>(depId));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void removeDepartementFromFavorites(final String depId) {
        compositeDisposable.add(departementDisplayRepository.removeDepartementFromFavorites(depId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        departementDeletedEvent.setValue(new Event<String>(depId));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}