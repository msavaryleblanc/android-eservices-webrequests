package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.api.model.DepartementSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DepartementDisplayService {
    @GET("departements")
    Single<DepartementSearchResponse> searchDepartements();

    @GET("departements/{depId}")
    Single<Departement> getDepartement(@Path("depId") String depId);
}
