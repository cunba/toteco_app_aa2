package com.svalero.toteco_app_aa2.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.HomeContract;
import com.svalero.toteco_app_aa2.databinding.FragmentHomeBinding;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.domain.dto.PublicationToRecyclerView;
import com.svalero.toteco_app_aa2.presenter.HomePresenter;
import com.svalero.toteco_app_aa2.util.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View,
        AdapterView.OnItemLongClickListener {

    private FragmentHomeBinding binding;
    private HomePresenter presenter;
    private List<PublicationLocal> publicationLocals;
    private List<PublicationToRecyclerView> publicationsToRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter= new HomePresenter(this);
        initializePublicationsList();
        loadPublications();

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
        publicationLocals = new ArrayList<>();
        publicationsToRecyclerView = new ArrayList<>();
        convertPublications();
        createRecyclerView();
    }

    @Override
    public void convertPublications() {
        publicationsToRecyclerView.clear();
        List<PublicationToRecyclerView> publications = presenter.convertPublications();
        publicationsToRecyclerView.addAll(publications);
    }

    @Override
    public void createRecyclerView() {
        // Get the recycler view
        rv = binding.getRoot().findViewById(R.id.publication_recycler_view);
        rv.setHasFixedSize(true);

        // Use the linear layout
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lManager);

        // Create the adapter
        adapter = new RecyclerViewAdapter(getParentFragmentManager(), publicationsToRecyclerView, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void loadPublications() {
        publicationLocals.clear();
        publicationLocals.addAll(presenter.loadPublications());
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void refreshPublications() {
        loadPublications();
        convertPublications();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }


}