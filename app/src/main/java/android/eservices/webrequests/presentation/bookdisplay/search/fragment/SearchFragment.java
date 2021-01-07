package android.eservices.webrequests.presentation.bookdisplay.search.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.BookActionInterface;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.BookAdapter;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.BookItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.DepartementActionInterface;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.DepartementAdapter;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.DepartementItemViewModel;
import android.eservices.webrequests.presentation.viewmodel.BookFavoriteViewModel;
import android.eservices.webrequests.presentation.viewmodel.BookSearchViewModel;
import android.eservices.webrequests.presentation.viewmodel.DepartementFavoriteViewModel;
import android.eservices.webrequests.presentation.viewmodel.DepartementSearchViewModel;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.String.valueOf;


/*
 * TODO : uncheck favorite selection in search results when favorite unchecked from Favorite fragment
 */
public class SearchFragment extends Fragment implements DepartementActionInterface {

    public static final String TAB_NAME = "Search";
    private View rootView;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private DepartementAdapter departementAdapter;
    private ProgressBar progressBar;
    private DepartementSearchViewModel departementSearchViewModel;
    private DepartementFavoriteViewModel departementFavoriteViewModel;
    private boolean onList = true;

    private SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        setupRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar);
        rootView.findViewById(R.id.toggleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onList)
                {
                    onList = false;
                    recyclerView.setAdapter(departementAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(),2));
                }
                else
                {
                    onList = true;
                    recyclerView.setAdapter(departementAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                }
            }
        });

        registerViewModels();
    }

    private void registerViewModels() {
        departementSearchViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(DepartementSearchViewModel.class);
        departementFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(DepartementFavoriteViewModel.class);
        System.out.println("FVVM is " + departementFavoriteViewModel);

        departementSearchViewModel.getDepartement().observe(getViewLifecycleOwner(), new Observer<List<DepartementItemViewModel>>() {
            @Override
            public void onChanged(List<DepartementItemViewModel> departementItemViewModelList) {
                departementAdapter.bindViewModels(departementItemViewModelList);
            }
        });

        departementSearchViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    departementSearchViewModel.searchDepartements(s);
                } else {
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 1000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            departementSearchViewModel.searchDepartements(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        departementAdapter = new DepartementAdapter(this);
        recyclerView.setAdapter(departementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onFavoriteToggle(String depId, boolean isFavorite) {
        if (isFavorite) {
            System.out.println("Add FAVORITES");
            departementFavoriteViewModel.addDepartementToFavorite(depId);
        } else {
            System.out.println("Delete FAVORITES");
            departementFavoriteViewModel.removeDepartementFromFavorites(depId);
        }
    }
}
