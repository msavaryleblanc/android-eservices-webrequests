package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.data.api.DepartementDisplayService;
import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.api.model.DepartementSearchResponse;
import android.eservices.webrequests.data.entity.BookEntity;
import android.eservices.webrequests.data.repository.bookdisplay.local.BookDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.BookToBookEntityMapper;
import android.eservices.webrequests.data.repository.bookdisplay.remote.BookDisplayRemoteDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.remote.DepartementDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class DepartementDisplayDataRepository implements DepartementDisplayRepository {
    private BookDisplayLocalDataSource bookDisplayLocalDataSource;
    private DepartementDisplayRemoteDataSource departementDisplayRemoteDataSource;
    private BookToBookEntityMapper bookToBookEntityMapper;

    public DepartementDisplayDataRepository(BookDisplayLocalDataSource bookDisplayLocalDataSource,
                                            DepartementDisplayRemoteDataSource departementDisplayRemoteDataSource,
                                            BookToBookEntityMapper bookToBookEntityMapper) {
        this.bookDisplayLocalDataSource = bookDisplayLocalDataSource;
        this.departementDisplayRemoteDataSource = departementDisplayRemoteDataSource;
        this.bookToBookEntityMapper = bookToBookEntityMapper;
    }

    @Override
    public Single<DepartementSearchResponse> getDepartementSearchResponse(String keywords) {
        return departementDisplayRemoteDataSource.getDepartementSearchResponse()
                .zipWith(bookDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<DepartementSearchResponse, List<String>, DepartementSearchResponse>() {
                    @Override
                    public DepartementSearchResponse apply(DepartementSearchResponse departementSearchResponse, List<String> idList) throws Exception {
                        for (Departement departement : departementSearchResponse.getDepartementList()) {
                            if (idList.contains(departement.getId())) {
                                departement.setFavorite();
                            }
                        }
                        return departementSearchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<BookEntity>> getFavoriteBooks() {
        return bookDisplayLocalDataSource.loadFavorites();
    }

    @Override
    public Completable addBookToFavorites(String bookId) {
        /*
        return bookDisplayRemoteDataSource.getBookDetails(bookId)
                .map(new Function<Book, BookEntity>() {
                    @Override
                    public BookEntity apply(Book book) throws Exception {
                        return bookToBookEntityMapper.map(book);
                    }
                })
                .flatMapCompletable(new Function<BookEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(BookEntity bookEntity) throws Exception {
                        return bookDisplayLocalDataSource.addBookToFavorites(bookEntity);
                    }
                });
         */
        return null;
    }

    @Override
    public Completable removeBookFromFavorites(String bookId) {
        return bookDisplayLocalDataSource.deleteBookFromFavorites(bookId);
    }
}
