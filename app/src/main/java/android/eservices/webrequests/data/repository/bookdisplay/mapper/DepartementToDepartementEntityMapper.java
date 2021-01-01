package android.eservices.webrequests.data.repository.bookdisplay.mapper;

import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.entity.DepartementEntity;
import android.text.TextUtils;

public class DepartementToDepartementEntityMapper {

    public DepartementEntity map(Departement departement) {
        // Création du departement entity pour la BDD
        DepartementEntity departementEntity = new DepartementEntity();

        // Map Nom département
        departementEntity.setName(departement.getName());

        //Map ID département
        departementEntity.setId(departement.getId());

        //Map numéro département
        departementEntity.setDepNumber(departement.getDepId());

        // Map liste service
        //departementEntity.setServices(departement.getIdServices());

        //Map URL home page
        departementEntity.setHomePage(departement.getHomePage());

        //Map date
        departementEntity.setUpdateAt(departement.getDate());

        //Map URL Image
        if(departement.getImage() == null) {
            departementEntity.setThumbUrl("https://via.placeholder.com/100");
        }else{
            departementEntity.setThumbUrl(departement.getImage().getData());
        }

        return departementEntity;
    }
}
