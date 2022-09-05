package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.contract.LocationsContract;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.model.LocationsModel;
import com.svalero.toteco_app_aa2.view.LocationActivity;

import java.util.List;

public class LocationsPresenter implements LocationsContract.Presenter, LocationsContract.Model.LoadEstablishmentsListener {

    private LocationActivity view;
    private LocationsModel model;

    public LocationsPresenter(LocationActivity view) {
        this.view = view;
        model = new LocationsModel(view.getApplicationContext());
    }

    @Override
    public void loadEstablishments() {
        model.loadEstablishments(this);
    }

    @Override
    public void loadEstablishmentsSuccess(List<Establishment> establishments) {
        view.loadEstablishments(establishments);
    }

    @Override
    public void loadEstablishmentsError(String error) {
        view.showToast(error);
    }
}
