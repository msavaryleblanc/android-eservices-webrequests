package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartementSearchResponse {
    @SerializedName("sv")
    List<Departement> departementList;

    int totalItems;

    public List<Departement> getDepartementList() {
        return departementList;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
