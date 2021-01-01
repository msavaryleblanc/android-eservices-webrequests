package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.entity.DepartementEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DepartementEntity.class}, version = 1)
public abstract class DepartementDatabase extends RoomDatabase {
    public abstract DepartementDao departementDao();
}