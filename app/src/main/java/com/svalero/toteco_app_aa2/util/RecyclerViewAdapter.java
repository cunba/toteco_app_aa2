package com.svalero.toteco_app_aa2.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.dto.PublicationToRecyclerView;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.view.HomeFragment;
import com.svalero.toteco_app_aa2.view.dialog.DeletePublicationDialog;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<PublicationToRecyclerView> publications;
    private FragmentManager mFragment;
    private HomeFragment homeFragment;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCardTitle;
        private final ImageView ivCardImage;
        private final TextView tvCardProducts;
        private final TextView tvCardPrice;
        private final TextView tvCardPunctuation;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            // Define click listener for the ViewHolder's View

            tvCardTitle = (TextView) view.findViewById(R.id.card_title);
            ivCardImage = (ImageView) view.findViewById(R.id.card_image);
            tvCardProducts = (TextView) view.findViewById(R.id.card_products_list);
            tvCardPrice = (TextView) view.findViewById(R.id.card_price);
            tvCardPunctuation = (TextView) view.findViewById(R.id.card_punctuation);
        }

        public TextView getTvCardTitle() {
            return tvCardTitle;
        }

        public ImageView getIvCardImage() {
            return ivCardImage;
        }

        public TextView getTvCardProducts() {
            return tvCardProducts;
        }

        public TextView getTvCardPrice() {
            return tvCardPrice;
        }

        public TextView getTvCardPunctuation() {
            return tvCardPunctuation;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param fragment
     * @param dataSet  String[] containing the data to populate views to be used
     */
    public RecyclerViewAdapter(FragmentManager fragment, List<PublicationToRecyclerView> dataSet, HomeFragment homeFragment) {
        publications = dataSet;
        mFragment = fragment;
        this.homeFragment = homeFragment;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.publication_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Configuration of the listener when the publication is clicked
        viewHolder.view.setOnClickListener(v -> {
            AppDatabase db = Room.databaseBuilder(viewHolder.itemView.getContext(),
                            AppDatabase.class, "toteco").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            PublicationToRecyclerView publicationToRecyclerView = publications.get(position);
            PublicationLocal publicationLocal = db.publicationDao().findById(publicationToRecyclerView.getPublicationId());
            DialogFragment newFragment = new DeletePublicationDialog(homeFragment, publicationLocal);
            newFragment.show(mFragment, "delete");
        });

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getTvCardTitle().setText(publications.get(position).getEstablishmentName());

        Bitmap image = ImageAdapter.fromStringToBitmap(publications.get(position).getImage());
        viewHolder.getIvCardImage().setImageBitmap(image);

        AtomicReference<String> products = new AtomicReference<>("");
        publications.get(position).getProducts().stream().forEach(p ->
                products.set("" + products.get() + '\n' + p.toString() + '\n')
        );
        viewHolder.getTvCardProducts().setText(products.get());

        viewHolder.getTvCardPrice().setText(publications.get(position).getTotalPrice());
        viewHolder.getTvCardPunctuation().setText(publications.get(position).getTotalPunctuation());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return publications.size();
    }

}

