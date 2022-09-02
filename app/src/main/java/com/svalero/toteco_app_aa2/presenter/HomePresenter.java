package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.contract.HomeContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.dto.PublicationToRecyclerView;
import com.svalero.toteco_app_aa2.model.HomeModel;
import com.svalero.toteco_app_aa2.view.HomeFragment;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeModel model;
    private HomeFragment view;

    public HomePresenter(HomeFragment view) {
        this.view = view;
        model = new HomeModel(view.getContext());
    }

    @Override
    public List<Publication> loadPublications() {
        return model.loadPublications();
    }

    @Override
    public List<PublicationToRecyclerView> convertPublications() {
        return model.convertPublications();
    }

    @Override
    public void deleteUnsavedProducts() {
        model.deleteUnsavedProducts();
    }

}
