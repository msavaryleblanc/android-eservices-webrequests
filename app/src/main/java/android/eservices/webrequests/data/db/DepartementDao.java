package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.entity.DepartementEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface DepartementDao {

    @Query("SELECT * from departemententity")
    Flowable<List<DepartementEntity>> loadFavorites();

    @Insert
    public Completable addDepartementToFavorites(DepartementEntity departementEntity);

    @Query("DELETE FROM departemententity WHERE id = :id")
    public Completable deleteDepartementFromFavorites(String id);

    @Query("SELECT id from departemententity")
    Single<List<String>> getFavoriteIdList();
}
