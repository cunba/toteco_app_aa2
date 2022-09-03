package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;

import com.svalero.toteco_app_aa2.contract.dialog.ProductDialogContract;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.domain.dto.ProductDialogDTO;
import com.svalero.toteco_app_aa2.model.dialog.ProductDialogModel;
import com.svalero.toteco_app_aa2.view.dialog.ProductDialog;

public class ProductDialogPresenter implements ProductDialogContract.Presenter {

    private final ProductDialogModel model;
    private final ProductDialog view;

    public ProductDialogPresenter(ProductDialog view, Context context) {
        this.view = view;
        model = new ProductDialogModel(context);
    }

    @Override
    public String addProduct(ProductDialogDTO productDialogDTO) {
        return model.addProduct(productDialogDTO);
    }

    @Override
    public String modifyProduct(ProductDialogDTO productDialogDTO, ProductLocal productLocal) {
        return model.modifyProduct(productDialogDTO, productLocal);
    }

}
