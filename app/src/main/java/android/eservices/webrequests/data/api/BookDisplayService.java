package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.api.model.DepartementSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookDisplayService {
    @GET("departements")
    Single<BookSearchResponse> searchBooks(@Query("q") String keywords);

    @GET("departements/{depId}")
    Single<Book> getBook(@Path("depId") String depId);
}
