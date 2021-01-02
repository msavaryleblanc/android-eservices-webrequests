package android.eservices.webrequests.presentation.bookdisplay.favorite.adapter;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.api.model.Departement;
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

public class DepartementDetailAdapter extends RecyclerView.Adapter<DepartementDetailAdapter.DepartementDetailViewHolder> {


    public static class DepartementDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView languageTextView;
        private TextView descriptionTextView;
        private TextView publishedTextView;
        private ImageView iconImageView;
        private View v;
        private DepartementDetailViewModel departementDetailViewModel;
        private DepartementDetailActionInterface departementDetailActionInterface;
        private Switch favoriteSwitch;

        public DepartementDetailViewHolder(View v, final DepartementDetailActionInterface departementDetailActionInterface) {
            super(v);
            this.v = v;
            /*
            titleTextView = v.findViewById(R.id.book_title_textview);
            languageTextView = v.findViewById(R.id.book_language_textview);
            descriptionTextView = v.findViewById(R.id.book_description_textview);
            publishedTextView = v.findViewById(R.id.book_published_textview);
            authorsTextView = v.findViewById(R.id.book_authors_textview);
            iconImageView = v.findViewById(R.id.book_icon_imageview);
            */
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            setupListeners();
            this.departementDetailActionInterface = departementDetailActionInterface;
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!b) {
                        departementDetailActionInterface.onRemoveFavorite(departementDetailViewModel.getDepartementId());
                    }
                }
            });
        }

        void bind(DepartementDetailViewModel departementDetailViewModel) {
            this.departementDetailViewModel = departementDetailViewModel;
            /*
            titleTextView.setText(bookDetailViewModel.getBookTitle());
            authorsTextView.setText(bookDetailViewModel.getBookAuthors());
            languageTextView.setText(bookDetailViewModel.getBookLanguage());
            descriptionTextView.setText(bookDetailViewModel.getBookDescription());
             */
            favoriteSwitch.setChecked(true);
            /*
            if (bookDetailViewModel.getBookDescription() == null) {
                descriptionTextView.setVisibility(View.GONE);
            } else {
                descriptionTextView.setVisibility(View.VISIBLE);
            }
            publishedTextView.setText(bookDetailViewModel.getBookPublishedDate());
            Glide.with(v)
                    .load(bookDetailViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);

             */

        }

    }

    private List<DepartementDetailViewModel> departementDetailViewModelList;
    private DepartementDetailActionInterface departementDetailActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DepartementDetailAdapter(DepartementDetailActionInterface departementDetailActionInterface) {
        departementDetailViewModelList = new ArrayList<>();
        this.departementDetailActionInterface = departementDetailActionInterface;
    }

    public void bindViewModels(List<DepartementDetailViewModel> departementItemViewModelList) {
        this.departementDetailViewModelList.clear();
        this.departementDetailViewModelList.addAll(departementItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DepartementDetailViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detailed_book, parent, false);
        DepartementDetailViewHolder departementDetailViewHolder = new DepartementDetailViewHolder(v, departementDetailActionInterface);
        return departementDetailViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DepartementDetailViewHolder holder, int position) {
        holder.bind(departementDetailViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return departementDetailViewModelList.size();
    }


}