package android.eservices.webrequests.presentation.bookdisplay.search.adapter;

import android.eservices.webrequests.R;
import android.eservices.webrequests.utils.GlideApp;
import android.eservices.webrequests.utils.MyAppGlideModule;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class DepartementAdapter extends RecyclerView.Adapter<DepartementAdapter.DepartementViewHolder> {


    public static class DepartementViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView depNumberTextView;
        private ImageView iconImageView;
        private View v;
        private DepartementItemViewModel departementItemViewModel;
        private DepartementActionInterface departementActionInterface;
        private Switch favoriteSwitch;

        public DepartementViewHolder(View v, final DepartementActionInterface departementActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.book_title_textview);
            depNumberTextView = v.findViewById(R.id.book_authors_textview);
            iconImageView = v.findViewById(R.id.book_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            this.departementActionInterface = departementActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    departementActionInterface.onFavoriteToggle(departementItemViewModel.getDepNumber(), b);
                }
            });
        }

        void bind(DepartementItemViewModel departementItemViewModel) {
            this.departementItemViewModel = departementItemViewModel;
            titleTextView.setText(departementItemViewModel.getDepName());
            depNumberTextView.setText(String.valueOf(departementItemViewModel.getDepNumber()));
            favoriteSwitch.setChecked(departementItemViewModel.isFavorite());
            GlideApp.with(v)
                    .load(departementItemViewModel.getLogo())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);

        }

    }

    private List<DepartementItemViewModel> departementItemViewModelList;
    private DepartementActionInterface departementActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DepartementAdapter(DepartementActionInterface departementActionInterface) {
        departementItemViewModelList = new ArrayList<>();
        this.departementActionInterface = departementActionInterface;
    }

    public void bindViewModels(List<DepartementItemViewModel> departementItemViewModelList) {
        this.departementItemViewModelList.clear();
        this.departementItemViewModelList.addAll(departementItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DepartementViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        DepartementViewHolder departementViewHolder = new DepartementViewHolder(v, departementActionInterface);
        return departementViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DepartementViewHolder holder, int position) {
        holder.bind(departementItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return departementItemViewModelList.size();
    }


}