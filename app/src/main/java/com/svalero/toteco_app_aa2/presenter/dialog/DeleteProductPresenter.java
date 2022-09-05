package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;

import com.svalero.toteco_app_aa2.contract.dialog.DeleteProductContract;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.model.dialog.DeleteProductModel;
import com.svalero.toteco_app_aa2.view.dialog.DeleteProductDialog;

public class DeleteProductPresenter implements DeleteProductContract.Presenter {
    private final DeleteProductModel model;
    private final DeleteProductDialog view;

    public DeleteProductPresenter(DeleteProductDialog view, Context context) {
        this.view = view;
        model = new DeleteProductModel(context);
    }

    @Override
    public void delete(ProductLocal productLocal) {
        model.delete(productLocal);
    }
}
