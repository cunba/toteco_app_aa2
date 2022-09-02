package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.dto.ProductDialogDTO;

public interface ProductDialogContract {
    interface Model {
        String addProduct(ProductDialogDTO productDialogDTO);
        String modifyProduct(ProductDialogDTO productDialogDTO, Product product);
    }

    interface View {
        void onPressSubmit(android.view.View view);
    }

    interface Presenter {
        String addProduct(ProductDialogDTO productDialogDTO);
        String modifyProduct(ProductDialogDTO productDialogDTO, Product product);
    }
}
