package android.eservices.webrequests.presentation.bookdisplay.favorite.mapper;

import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.entity.DepartementEntity;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.DepartementDetailViewModel;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

public class DepartementEntityToDetailViewModelMapper {

    private DepartementDetailViewModel map(DepartementEntity departementEntity) {
        DepartementDetailViewModel departementItemViewModel = new DepartementDetailViewModel();

        // Map ID
        departementItemViewModel.setDepartementId(departementEntity.getId());

        //Map DEP NAME
        departementItemViewModel.setDepartementName(departementEntity.getName());

        //Map HOME PAGE
        departementItemViewModel.setHomePage(departementEntity.getHomePage());

        //Map
        departementItemViewModel.setDepNum(departementEntity.getDepNumber());

        //Map Image
        departementItemViewModel.setIconUrl(departementEntity.getThumbUrl());

        // Map Update
        departementItemViewModel.setUpdateAt(departementEntity.getUpdateAt());

        return departementItemViewModel;
    }

    public List<DepartementDetailViewModel> map(List<DepartementEntity> departementList) {
        List<DepartementDetailViewModel> departementItemViewModelList = new ArrayList<>();
        for (DepartementEntity departement : departementList) {
            departementItemViewModelList.add(map(departement));
        }
        return departementItemViewModelList;
    }

}
