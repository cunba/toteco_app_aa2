package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;

import com.svalero.toteco_app_aa2.contract.dialog.AddEstablishmentContract;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.dto.AddEstablishmentDTO;
import com.svalero.toteco_app_aa2.model.dialog.AddEstablishmentModel;
import com.svalero.toteco_app_aa2.view.dialog.AddEstablishmentDialog;

import java.util.List;

public class AddEstablishmentPresenter implements AddEstablishmentContract.Presenter {
    private final AddEstablishmentModel model;
    private final AddEstablishmentDialog view;

    public AddEstablishmentPresenter(AddEstablishmentDialog view, Context context) {
        this.view = view;
        model = new AddEstablishmentModel(context);
    }

    @Override
    public List<Establishment> loadEstablishments() {
        return model.loadEstablishments();
    }

    @Override
    public String onPressSubmit(AddEstablishmentDTO addEstablishmentDTO) {
        return model.onPressSubmit(addEstablishmentDTO);
    }

    @Override
    public List<Establishment> findByName(String name) {
        return model.findByName(name);
    }
}
