package android.eservices.webrequests.data.repository.bookdisplay.remote;

import android.eservices.webrequests.data.api.DepartementDisplayService;
import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.api.model.DepartementSearchResponse;
import android.eservices.webrequests.data.api.model.DepartementWithServices;

import io.reactivex.Single;

public class DepartementDisplayRemoteDataSource {
    private DepartementDisplayService departementDisplayService;

    public DepartementDisplayRemoteDataSource(DepartementDisplayService departementDisplayService) {
        this.departementDisplayService = departementDisplayService;
    }

    public Single<DepartementSearchResponse> getDepartementSearchResponse() {
        return departementDisplayService.searchDepartements();
    }

    public Single<Departement> getDepartementDetails(String departementId) {
        return departementDisplayService.getDepartement(departementId);
    }

    public Single<DepartementWithServices> getDepartementWithServices(String depId) {
        return departementDisplayService.getDepartementWithServices(depId);
    }
}
