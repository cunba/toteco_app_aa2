package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.contract.AddPublicationContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.domain.dto.view.AddPublicationSummaryDTO;
import com.svalero.toteco_app_aa2.model.AddPublicationModel;
import com.svalero.toteco_app_aa2.view.AddPublicationFragment;

import java.util.List;

public class AddPublicationPresenter implements AddPublicationContract.Presenter {

    private AddPublicationModel model;
    private AddPublicationFragment view;

    public AddPublicationPresenter(AddPublicationFragment view) {
        this.view = view;
        model = new AddPublicationModel(view.getContext(), this);
    }

    @Override
    public List<ProductLocal> loadProducts() {
        return model.loadProducts();
    }

    @Override
    public AddPublicationSummaryDTO makeSummary(double establishmentPunctuation) {
        return model.makeSummary(establishmentPunctuation);
    }

    @Override
    public void onPressSubmit(byte[] image) {
        model.onPressSubmit(image);
    }

    @Override
    public EstablishmentLocal getEstablishment() {
        return model.getEstablishment();
    }

    @Override
    public void onSubmit(String message) {
        view.onSubmit(message);
    }
}
