package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.contract.LocationsContract;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.model.LocationsModel;
import com.svalero.toteco_app_aa2.view.LocationsFragment;

import java.util.List;

public class LocationsPresenter implements LocationsContract.Presenter {

    private LocationsFragment view;
    private LocationsModel model;

    public LocationsPresenter(LocationsFragment view) {
        this.view = view;
        model = new LocationsModel(view.getContext());
    }

    @Override
    public List<Establishment> loadEstablishments() {
        return model.loadEstablishments();
    }
}
