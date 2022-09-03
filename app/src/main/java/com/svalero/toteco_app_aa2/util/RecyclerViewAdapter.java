package com.svalero.toteco_app_aa2.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.view.HomeFragment;
import com.svalero.toteco_app_aa2.view.dialog.DeletePublicationDialog;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Publication> publications;
    private FragmentManager mFragment;
    private HomeFragment homeFragment;
    private ArrayAdapter<Product> productsAdapter;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPublicationItemEstablishment;
        private final TextView tvPublicationItemUsername;
        private final ImageView ivPublicationItemImage;
//        private final TextView tvPublicationItemProducts;
        private final TextView tvPublicationItemPrice;
        private final TextView tvPublicationItemPunctuation;
        private final ListView lvPublicationItemProductList;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            // Define click listener for the ViewHolder's View

            tvPublicationItemEstablishment = view.findViewById(R.id.publication_item_establishment);
            tvPublicationItemUsername = view.findViewById(R.id.publication_item_username);
            ivPublicationItemImage = view.findViewById(R.id.publication_item_image);
//            tvPublicationItemProducts = view.findViewById(R.id.publication_item_products_list);
            tvPublicationItemPrice = view.findViewById(R.id.publication_item_price);
            tvPublicationItemPunctuation = view.findViewById(R.id.publication_item_punctuation);
            lvPublicationItemProductList = view.findViewById(R.id.publication_item_products_list);
        }

        public TextView getTvPublicationItemEstablishment() {
            return tvPublicationItemEstablishment;
        }

        public TextView getTvPublicationItemUsername() {
            return tvPublicationItemUsername;
        }

        public ImageView getIvPublicationItemImage() {
            return ivPublicationItemImage;
        }

//        public TextView getTvPublicationItemProducts() {
//            return tvPublicationItemProducts;
//        }
        public ListView getLvPublicationItemProductList() {
            return lvPublicationItemProductList;
        }

        public TextView getTvPublicationItemPrice() {
            return tvPublicationItemPrice;
        }

        public TextView getTvPublicationItemPunctuation() {
            return tvPublicationItemPunctuation;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param fragment
     * @param dataSet  String[] containing the data to populate views to be used
     */
    public RecyclerViewAdapter(FragmentManager fragment, List<Publication> dataSet, ArrayAdapter<Product> productsAdapter, HomeFragment homeFragment) {
        publications = dataSet;
        mFragment = fragment;
        this.homeFragment = homeFragment;
        this.productsAdapter = productsAdapter;
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
            Publication publication = publications.get(position);
            DialogFragment newFragment = new DeletePublicationDialog(homeFragment, publication);
            newFragment.show(mFragment, "delete");
        });

        // Getting the user
        AppDatabase db = Room.databaseBuilder(viewHolder.itemView.getContext(),
                        AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        List<UserLocal> user = db.userDao().findAll();

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getTvPublicationItemEstablishment().setText(publications.get(position).getEstablishment().getName());
        viewHolder.getTvPublicationItemUsername().setText(user.get(0).getUsername());

        Bitmap image = ImageAdapter.fromStringToBitmap(publications.get(position).getPhoto());
        viewHolder.getIvPublicationItemImage().setImageBitmap(image);

        productsAdapter.clear();
        productsAdapter.addAll(publications.get(position).getProducts());
        viewHolder.getLvPublicationItemProductList().setAdapter(productsAdapter);

//        AtomicReference<String> products = new AtomicReference<>("");
//        publications.get(position).getProducts().stream().forEach(p ->
//                products.set("" + products.get() + '\n' + p.toString() + '\n')
//        );
//        viewHolder.getTvPublicationItemProducts().setText(products.get());

        double totalPrice = Utils.roundNumber(publications.get(position).getTotalPrice());
        viewHolder.getTvPublicationItemPrice().setText(String.valueOf(totalPrice));

        double totalPunctuation = Utils.roundNumber(publications.get(position).getTotalPunctuation());
        viewHolder.getTvPublicationItemPunctuation().setText(String.valueOf(totalPunctuation));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return publications.size();
    }

}

