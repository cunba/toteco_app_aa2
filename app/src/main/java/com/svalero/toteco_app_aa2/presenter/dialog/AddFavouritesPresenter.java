package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;

import com.svalero.toteco_app_aa2.contract.dialog.AddFavouritesContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.model.dialog.AddFavouritesModel;
import com.svalero.toteco_app_aa2.view.dialog.AddFavouritesDialog;

public class AddFavouritesPresenter implements AddFavouritesContract.Presenter {
    private final AddFavouritesDialog view;
    private final AddFavouritesModel model;

    public AddFavouritesPresenter(AddFavouritesDialog view, Context context) {
        this.view = view;
        model = new AddFavouritesModel(context);
    }

    @Override
    public void addFavourite(Publication publication) {
        model.addFavourite(publication);
    }
}
