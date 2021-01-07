package android.eservices.webrequests.presentation.bookdisplay.favorite.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.BookDetailAdapter;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.BookDetailViewModel;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.DepartementDetailActionInterface;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.DepartementDetailAdapter;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.DepartementDetailViewModel;
import android.eservices.webrequests.presentation.viewmodel.DepartementFavoriteViewModel;
import android.eservices.webrequests.presentation.viewmodel.Event;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteFragment extends Fragment implements DepartementDetailActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    private RecyclerView recyclerView;
    private DepartementDetailAdapter departementAdapter;
    private DepartementFavoriteViewModel departementFavoriteViewModel;

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        departementFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(DepartementFavoriteViewModel.class);
        System.out.println("FVVM is " + departementFavoriteViewModel);

        departementFavoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<DepartementDetailViewModel>>() {
            @Override
            public void onChanged(List<DepartementDetailViewModel> departementDetailViewModelList) {
                departementAdapter.bindViewModels(departementDetailViewModelList);
            }
        });

        departementFavoriteViewModel.getDepartementAddedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });

        departementFavoriteViewModel.getDepartementDeletedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        departementAdapter = new DepartementDetailAdapter(this);
        recyclerView.setAdapter(departementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onRemoveFavorite(String departementId) {
        departementFavoriteViewModel.removeDepartementFromFavorites(departementId);
        System.out.println("Remove book " + departementId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //bookFavoritePresenter.detachView();
    }
}
