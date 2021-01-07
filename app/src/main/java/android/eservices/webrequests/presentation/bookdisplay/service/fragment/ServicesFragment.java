package android.eservices.webrequests.presentation.bookdisplay.service.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.DepartementAdapter;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.DepartementItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.service.adapter.ServiceAdapter;
import android.eservices.webrequests.presentation.bookdisplay.service.adapter.ServiceItemViewModel;
import android.eservices.webrequests.presentation.viewmodel.DepartementFavoriteViewModel;
import android.eservices.webrequests.presentation.viewmodel.DepartementSearchViewModel;
import android.eservices.webrequests.presentation.viewmodel.ServiceViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class ServicesFragment extends Fragment {
    public static final String TAB_NAME = "SERVICES";
    private View rootView;
    private RecyclerView recyclerView;
    private ServiceAdapter serviceAdapter;
    private ProgressBar progressBar;
    private ServiceViewModel serviceViewModel;
    private boolean onList = true;
    private String depId;
    private String depName;
    private TextView title;
    private TextView idTextView;

    public ServicesFragment(String depId,String depName) {
        this.depId = depId;
        this.depName = depName;
    }

    /*
    public static ServicesFragment newInstance() {
        return new ServicesFragment("2");
    }
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_services, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = rootView.findViewById(R.id.name_textView);
        title.setText(depName);

        idTextView = rootView.findViewById(R.id.id_textView);
        idTextView.setText(depId);

        registerViewModels();
        setupServicesView();
        setupRecyclerView();
    }

    private void registerViewModels() {
        serviceViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ServiceViewModel.class);
        serviceViewModel.getService().observe(getViewLifecycleOwner(), new Observer<List<ServiceItemViewModel>>() {
            @Override
            public void onChanged(List<ServiceItemViewModel> serviceItemViewModelList) {
                serviceAdapter.bindViewModels(serviceItemViewModelList);
            }
        });
    }

    private void setupServicesView() {
        serviceViewModel.getDepartementWithServices(this.depId);
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        serviceAdapter = new ServiceAdapter();
        recyclerView.setAdapter(serviceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
