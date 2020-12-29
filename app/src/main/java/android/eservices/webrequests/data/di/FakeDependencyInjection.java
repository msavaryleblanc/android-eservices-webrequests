package android.eservices.webrequests.data.di;

import android.content.Context;
import android.eservices.webrequests.data.api.BookDisplayService;
import android.eservices.webrequests.data.api.DepartementDisplayService;
import android.eservices.webrequests.data.db.BookDatabase;
import android.eservices.webrequests.data.repository.bookdisplay.BookDisplayRepository;
import android.eservices.webrequests.data.repository.bookdisplay.DepartementDisplayDataRepository;
import android.eservices.webrequests.data.repository.bookdisplay.DepartementDisplayRepository;
import android.eservices.webrequests.data.repository.bookdisplay.local.BookDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.BookToBookEntityMapper;
import android.eservices.webrequests.data.repository.bookdisplay.remote.DepartementDisplayRemoteDataSource;
import android.eservices.webrequests.presentation.viewmodel.ViewModelFactory;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection {

    private static DepartementDisplayService departementDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static DepartementDisplayRepository departementDisplayRepository;
    private static BookDatabase bookDatabase;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getDepartementDisplayRepository());
        }
        return viewModelFactory;
    }


    public static DepartementDisplayRepository getDepartementDisplayRepository() {
        if (departementDisplayRepository == null) {
            departementDisplayRepository = new DepartementDisplayDataRepository(
                    new BookDisplayLocalDataSource(getBookDatabase()),
                    new DepartementDisplayRemoteDataSource(getDepartementDisplayService()),
                    new BookToBookEntityMapper()
            );
        }
        return departementDisplayRepository;
    }

    public static DepartementDisplayService getDepartementDisplayService() {
        if (departementDisplayService == null) {
            departementDisplayService = getRetrofit().create(DepartementDisplayService.class);
        }
        return departementDisplayService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.r0uge.eu")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static BookDatabase getBookDatabase() {
        if (bookDatabase == null) {
            bookDatabase = Room.databaseBuilder(applicationContext,
                    BookDatabase.class, "book-database").build();
        }
        return bookDatabase;
    }
}
