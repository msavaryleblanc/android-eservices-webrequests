package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.data.api.model.DepartementSearchResponse;
import android.eservices.webrequests.data.api.model.DepartementWithServices;
import android.eservices.webrequests.data.entity.DepartementEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DepartementDisplayRepository {
    Single<DepartementSearchResponse> getDepartementSearchResponse(String keywords);

    Single<DepartementWithServices> getDepartementWithServices(String depId);

    Flowable<List<DepartementEntity>> getFavoriteDepartement();

    Completable addDepartementToFavorites(String depId);

    Completable removeDepartementFromFavorites(String depId);
}
