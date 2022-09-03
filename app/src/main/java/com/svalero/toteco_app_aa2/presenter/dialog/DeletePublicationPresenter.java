package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;

import com.svalero.toteco_app_aa2.contract.dialog.DeletePublicationContract;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.model.dialog.DeletePublicationModel;
import com.svalero.toteco_app_aa2.view.dialog.DeletePublicationDialog;

public class DeletePublicationPresenter implements DeletePublicationContract.Presenter {
    private final DeletePublicationModel model;
    private final DeletePublicationDialog view;

    public DeletePublicationPresenter(DeletePublicationDialog view, Context context) {
        this.view = view;
        model = new DeletePublicationModel(context);
    }

    @Override
    public void delete(PublicationLocal publicationLocal) {
        model.delete(publicationLocal);
    }
}
