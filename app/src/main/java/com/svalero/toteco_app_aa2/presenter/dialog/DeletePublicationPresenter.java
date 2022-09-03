package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;

import com.svalero.toteco_app_aa2.contract.dialog.DeletePublicationContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.model.dialog.DeletePublicationModel;
import com.svalero.toteco_app_aa2.view.dialog.DeletePublicationDialog;

public class DeletePublicationPresenter implements DeletePublicationContract.Presenter, DeletePublicationContract.Model.DeleteListener {
    private final DeletePublicationModel model;
    private final DeletePublicationDialog view;

    public DeletePublicationPresenter(DeletePublicationDialog view, Context context) {
        this.view = view;
        model = new DeletePublicationModel(context);
    }

    @Override
    public void delete(Publication publication) {
        model.delete(this, publication);
    }

    @Override
    public void deleteSuccess(String message) {
        view.onDelete(message);
    }

    @Override
    public void deleteError(String message) {
        view.onDelete(message);
    }
}
