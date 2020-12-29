package android.eservices.webrequests.presentation.bookdisplay.search.mapper;

import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.BookItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.DepartementItemViewModel;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class DepartementToViewModelMapper {
    private DepartementItemViewModel map(Departement departement) {
        DepartementItemViewModel departementItemViewModel = new DepartementItemViewModel();
        departementItemViewModel.setDepName(departement.getName());
        departementItemViewModel.setDepNumber(departement.getDepId());
        if (departement.getImage() != null) {
            departementItemViewModel.setLogo(departement.getImage().getData());
        }
        departementItemViewModel.setFavorite(departement.isFavorite());
        /*
        if (departement.getVolumeInfo().getAuthorList() == null) {
            departementItemViewModel.setBookAuthors("N.C.");
        } else {
            departementItemViewModel.setBookAuthors(TextUtils.join(", ", book.getVolumeInfo().getAuthorList()));
        }
        */
        return departementItemViewModel;
    }

    public List<DepartementItemViewModel> map(List<Departement> departementList) {
        List<DepartementItemViewModel> departementItemViewModelList = new ArrayList<>();
        for (Departement departement : departementList) {
            departementItemViewModelList.add(map(departement));
        }
        return departementItemViewModelList;
    }
}
