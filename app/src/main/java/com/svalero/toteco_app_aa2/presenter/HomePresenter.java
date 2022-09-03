package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.contract.HomeContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.model.HomeModel;
import com.svalero.toteco_app_aa2.view.HomeFragment;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter, HomeContract.Model.LoadPublicationsListener {

    private final HomeModel model;
    private final HomeFragment view;

    public HomePresenter(HomeFragment view) {
        this.view = view;
        model = new HomeModel(view.getContext());
    }

    @Override
    public void loadPublications() {
        model.loadPublications(this);
    }

    @Override
    public void deleteUnsavedProducts() {
        model.deleteUnsavedLocalProducts();
    }

    @Override
    public void loadPublicationsSuccess(List<Publication> publications) {
        view.loadPublications(publications);
    }

    @Override
    public void loadPublicationsError(String error) {
        view.showToast(error);
    }
}
