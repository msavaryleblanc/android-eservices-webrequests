package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.repository.bookdisplay.DepartementDisplayRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final DepartementDisplayRepository departementDisplayRepository;

    public ViewModelFactory(DepartementDisplayRepository departementDisplayRepository) {
        this.departementDisplayRepository = departementDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DepartementSearchViewModel.class)) {
            return (T) new DepartementSearchViewModel(departementDisplayRepository);
        }
        if (modelClass.isAssignableFrom(BookFavoriteViewModel.class)) {
            //return (T) new BookFavoriteViewModel(departementDisplayRepository);
            return null;
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}