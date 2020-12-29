package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.DepartementSearchResponse;
import android.eservices.webrequests.data.repository.bookdisplay.DepartementDisplayRepository;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.BookItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.DepartementItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.search.mapper.DepartementToViewModelMapper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DepartementSearchViewModel extends ViewModel {
    private DepartementDisplayRepository departementDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private DepartementToViewModelMapper departementToViewModelMapper;

    public DepartementSearchViewModel(DepartementDisplayRepository departementDisplayRepository) {
        this.departementDisplayRepository = departementDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.departementToViewModelMapper = new DepartementToViewModelMapper();
    }

    private MutableLiveData<List<DepartementItemViewModel>> departement = new MutableLiveData<List<DepartementItemViewModel>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<DepartementItemViewModel>> getDepartement() {
        return departement;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void searchBooks(String keywords) {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(departementDisplayRepository.getDepartementSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DepartementSearchResponse>() {

                    @Override
                    public void onSuccess(DepartementSearchResponse departementSearchResponse) {
                        departement.setValue(departementToViewModelMapper.map(departementSearchResponse.getDepartementList()));
                        isDataLoading.setValue(false);
                        Log.i("tag", "onSuccess: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                        Log.i("tag", "onError: ");
                    }
                }));
    }

    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }

}
