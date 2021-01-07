package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.DepartementWithServices;
import android.eservices.webrequests.data.repository.bookdisplay.DepartementDisplayRepository;
import android.eservices.webrequests.presentation.bookdisplay.service.adapter.ServiceItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.service.mapper.ServiceToViewModelMapper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ServiceViewModel extends ViewModel {

    private DepartementDisplayRepository departementDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private ServiceToViewModelMapper serviceToViewModelMapper;

    public ServiceViewModel(DepartementDisplayRepository departementDisplayRepository) {
        this.departementDisplayRepository = departementDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.serviceToViewModelMapper = new ServiceToViewModelMapper();
    }

    private MutableLiveData<List<ServiceItemViewModel>> service = new MutableLiveData<List<ServiceItemViewModel>>();
    //private MutableLiveData<List<DepartementItemViewModel>> service = new MutableLiveData<List<DepartementItemViewModel>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<ServiceItemViewModel>> getService() {
        return service;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void getDepartementWithServices(String depId) {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(departementDisplayRepository.getDepartementWithServices(depId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DepartementWithServices>() {

                    @Override
                    public void onSuccess(DepartementWithServices departementWithServices) {
                        service.setValue(serviceToViewModelMapper.map(departementWithServices.getServices()));
                        isDataLoading.setValue(false);
                        Log.i("tag", "onSuccess: "+departementWithServices.getServices().length);
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
}
