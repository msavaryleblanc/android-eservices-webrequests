package android.eservices.webrequests.data.api.model;

public class Departement {

    private String _id;
    private String name;
    private String[] services;
    private int dep_id; //Numéro du département
    private Image img;
    private String homePage; //Lien vers la page principale des archives du département
    private String updateAt;
    private boolean isFavorite;

    public String getId() {
        return _id;
    }

    public int getDepId() {
        return dep_id;
    }

    public String[] getIdServices()
    {
        return services;
    }

    public Image getImage()
    {
        return img;
    }

    public String getHomePage()
    {
        return homePage;
    }

    public String getDate()
    {
        return updateAt;
    }

    public void setFavorite() {
        isFavorite = true;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getName() {
        return name;
    }
}
