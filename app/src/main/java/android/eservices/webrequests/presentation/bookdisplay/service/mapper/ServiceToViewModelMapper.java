package android.eservices.webrequests.presentation.bookdisplay.service.mapper;

import android.eservices.webrequests.data.api.model.Service;
import android.eservices.webrequests.presentation.bookdisplay.service.adapter.ServiceItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ServiceToViewModelMapper {

    private ServiceItemViewModel map(Service service) {
        ServiceItemViewModel serviceItemViewModel = new ServiceItemViewModel();
        serviceItemViewModel.setServiceName(service.getService_name());
        serviceItemViewModel.setStatus(service.getStatus());
        serviceItemViewModel.setLink(service.getLink());
        /*
        if (departement.getVolumeInfo().getAuthorList() == null) {
            departementItemViewModel.setBookAuthors("N.C.");
        } else {
            departementItemViewModel.setBookAuthors(TextUtils.join(", ", book.getVolumeInfo().getAuthorList()));
        }
        */
        return serviceItemViewModel;
    }

    public List<ServiceItemViewModel> map(Service[] serviceList) {
        List<ServiceItemViewModel> serviceItemViewModelList = new ArrayList<>();
        for (Service service : serviceList) {
            serviceItemViewModelList.add(map(service));
        }
        return serviceItemViewModelList;
    }
}
