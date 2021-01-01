package android.eservices.webrequests.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DepartementEntity {
    @NonNull
    @PrimaryKey
    public String id;
    public String name;
    public int depNumber;
    //public String[] services;
    public String homePage;
    private String thumbUrl;

    @ColumnInfo(name = "updated_at")
    private String updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepNumber() {
        return depNumber;
    }

    public void setDepNumber(int depNumber) {
        this.depNumber = depNumber;
    }

    /*
    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }
    */
    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
