package com.svalero.toteco_app_aa2.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.HomeContract;
import com.svalero.toteco_app_aa2.databinding.FragmentHomeBinding;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.presenter.HomePresenter;
import com.svalero.toteco_app_aa2.util.RecyclerViewAdapter;
import com.svalero.toteco_app_aa2.view.dialog.DeletePublicationDialog;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View,
        AdapterView.OnItemLongClickListener {

    private FragmentHomeBinding binding;
    private HomePresenter presenter;
    private List<Publication> publications;
    private ArrayAdapter<Product> productsAdapter;
    private RecyclerView.Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter = new HomePresenter(this);
        initializePublicationsList();
        presenter.loadPublications();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.deleteUnsavedProducts();
        refreshPublications();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initializePublicationsList() {
        publications = new ArrayList<>();
        createRecyclerView();
//        createProductsAdapter();
    }

//    private void createProductsAdapter() {
//        ArrayList<Product> products = new ArrayList<>();
//        productsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, products);
//    }

    private void createRecyclerView() {
        // Get the recycler view
        RecyclerView rv = binding.getRoot().findViewById(R.id.publication_recycler_view);
        rv.setHasFixedSize(true);

        // Use the linear layout
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lManager);

        // Create the adapter
        adapter = new RecyclerViewAdapter(getParentFragmentManager(), publications, productsAdapter, this);
        rv.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void loadPublications(List<Publication> publications) {
        this.publications.clear();
        this.publications.addAll(publications);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void refreshPublications() {
        presenter.loadPublications();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Publication publication = publications.get(i);
        DialogFragment newFragment = new DeletePublicationDialog(this, publication);
        newFragment.show(getParentFragmentManager(), "delete");
        return true;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


}