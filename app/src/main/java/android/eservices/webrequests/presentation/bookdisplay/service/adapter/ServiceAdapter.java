package android.eservices.webrequests.presentation.bookdisplay.service.adapter;


import android.content.Intent;
import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.bookdisplay.ServiceDisplayActivity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ServiceAdapter extends RecyclerView.Adapter<android.eservices.webrequests.presentation.bookdisplay.service.adapter.ServiceAdapter.ServiceViewHolder> {


    public static class ServiceViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView statusTextView;
        private View v;
        private ServiceItemViewModel serviceItemViewModel;
        private Switch favoriteSwitch;

        public ServiceViewHolder(View v) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.title_textview);
            statusTextView = v.findViewById(R.id.detail_textview);
            setupListeners();
        }

        private void setupListeners()
        {
            this.v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://www.google.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);
                }
            });

        }

        void bind(ServiceItemViewModel serviceItemViewModel) {
            this.serviceItemViewModel = serviceItemViewModel;
            titleTextView.setText(serviceItemViewModel.getServiceName());
            statusTextView.setText(serviceItemViewModel.getStatus() + " / mis à jour le " + serviceItemViewModel.getUpdatedAt());
        }

    }

    private List<ServiceItemViewModel> serviceItemViewModelList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ServiceAdapter() {
        serviceItemViewModelList = new ArrayList<>();
    }

    public void bindViewModels(List<ServiceItemViewModel> serviceItemViewModelList) {
        this.serviceItemViewModelList.clear();
        this.serviceItemViewModelList.addAll(serviceItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        android.eservices.webrequests.presentation.bookdisplay.service.adapter.ServiceAdapter.ServiceViewHolder serviceViewHolder = new android.eservices.webrequests.presentation.bookdisplay.service.adapter.ServiceAdapter.ServiceViewHolder(v);
        return serviceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        holder.bind(serviceItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return serviceItemViewModelList.size();
    }


}

