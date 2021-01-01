package android.eservices.webrequests.presentation.bookdisplay.favorite.adapter;

public class DepartementDetailViewModel {

    private String departementId;
    private String iconUrl;
    private String departementName;
    private String[] services;
    private String homePage;
    private String updateAt;

    public int getDepNum() {
        return depNum;
    }

    public void setDepNum(int depNum) {
        this.depNum = depNum;
    }

    private int depNum;

    public String getDepartementId() {
        return departementId;
    }

    public void setDepartementId(String departementId) {
        this.departementId = departementId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

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
}
