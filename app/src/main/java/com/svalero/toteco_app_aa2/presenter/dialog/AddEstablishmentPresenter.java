package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.svalero.toteco_app_aa2.contract.dialog.AddEstablishmentContract;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.dto.view.AddEstablishmentDTO;
import com.svalero.toteco_app_aa2.model.dialog.AddEstablishmentModel;
import com.svalero.toteco_app_aa2.view.dialog.AddEstablishmentDialog;

import java.util.List;

public class AddEstablishmentPresenter implements AddEstablishmentContract.Presenter, AddEstablishmentContract.Model.LoadEstablishmentsListener {
    private final AddEstablishmentModel model;
    private final AddEstablishmentDialog view;
    private final Context context;

    public AddEstablishmentPresenter(AddEstablishmentDialog view, Context context) {
        this.view = view;
        model = new AddEstablishmentModel(context);
        this.context = context;
    }

    @Override
    public void loadEstablishments() {
        model.loadEstablishments(this);
    }

    @Override
    public Establishment findEstablishment(List<Establishment> establishments, Marker marker) {
        return model.findEstablishment(establishments, marker);
    }

    @Override
    public void onPressSubmit(AddEstablishmentDTO addEstablishmentDTO) {
        String error = model.onPressSubmit(addEstablishmentDTO);
        view.onSubmit(error);
    }

    @Override
    public void loadEstablishmentsSuccess(List<Establishment> establishments) {
        view.loadEstablishments(establishments);
    }

    @Override
    public void loadEstablishmentsError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }
}
