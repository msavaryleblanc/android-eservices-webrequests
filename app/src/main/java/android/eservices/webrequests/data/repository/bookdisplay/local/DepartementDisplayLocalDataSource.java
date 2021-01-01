package android.eservices.webrequests.data.repository.bookdisplay.local;

import android.eservices.webrequests.data.entity.DepartementEntity;
import android.eservices.webrequests.data.db.DepartementDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class DepartementDisplayLocalDataSource {

    private DepartementDatabase departementDatabase;

    public DepartementDisplayLocalDataSource(DepartementDatabase departementDatabase) {
        this.departementDatabase = departementDatabase;
    }

    public Flowable<List<DepartementEntity>> loadFavorites() {
        return departementDatabase.departementDao().loadFavorites();
    }

    public Completable addDepartementToFavorites(DepartementEntity departementEntity) {
        return departementDatabase.departementDao().addDepartementToFavorites(departementEntity);
    }

    public Completable deleteDepartementFromFavorites(String id) {
        return departementDatabase.departementDao().deleteDepartementFromFavorites(id);
    }

    public Single<List<String>> getFavoriteIdList() {
        return departementDatabase.departementDao().getFavoriteIdList();
    }
}
