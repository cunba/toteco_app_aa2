package com.svalero.toteco_app_aa2.presenter.dialog;

import android.content.Context;
import android.widget.Toast;

import com.svalero.toteco_app_aa2.contract.dialog.ProductDialogContract;
import com.svalero.toteco_app_aa2.domain.ProductType;
import com.svalero.toteco_app_aa2.domain.dto.view.ProductDialogDTO;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.model.dialog.ProductDialogModel;
import com.svalero.toteco_app_aa2.view.dialog.ProductDialog;

import java.util.List;

public class ProductDialogPresenter implements ProductDialogContract.Presenter, ProductDialogContract.Model.GetTypesListener {

    private final ProductDialogModel model;
    private final ProductDialog view;
    private final Context context;

    public ProductDialogPresenter(ProductDialog view, Context context) {
        this.view = view;
        this.context = context;
        model = new ProductDialogModel(context);
    }

    @Override
    public void getTypes() {
        model.getTypes(this);
    }

    @Override
    public void addProduct(ProductDialogDTO productDialogDTO) {
        String error = model.addProduct(productDialogDTO);
        view.onSubmit(error);
    }

    @Override
    public void modifyProduct(ProductDialogDTO productDialogDTO, ProductLocal productLocal) {
        String error = model.modifyProduct(productDialogDTO, productLocal);
        view.onSubmit(error);
    }

    @Override
    public void getTypesSuccess(List<ProductType> types) {
        view.setSpinner(types);
    }

    @Override
    public void getTypesError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }
}
