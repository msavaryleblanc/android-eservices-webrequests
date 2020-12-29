package android.eservices.webrequests.data.api.model;

public class Service {
    private String id;
    private String depId;
    private String depName;
    private String status; //Online ou offline
    private String serviceLink;
    private ImageLinks image;


    public String getId() {
        return id;
    }

    public String getDepId() {
        return depId;
    }

    public String getDepName() {
        return depName;
    }

    public String getStatus() {
        return status;
    }

    public String getServiceLink() {
        return serviceLink;
    }

    public ImageLinks getImage() {
        return image;
    }

}
