package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.ProductType;
import com.svalero.toteco_app_aa2.domain.dto.view.ProductDialogDTO;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;

import java.util.List;

public interface ProductDialogContract {
    interface Model {
        interface GetTypesListener {
            void getTypesSuccess(List<ProductType> types);

            void getTypesError(String error);
        }

        void getTypes(GetTypesListener listener);

        String addProduct(ProductDialogDTO productDialogDTO);

        String modifyProduct(ProductDialogDTO productDialogDTO, ProductLocal productLocal);
    }

    interface View {
        void onPressSubmit();

        void onSubmit(String error);
    }

    interface Presenter {
        void getTypes();

        String addProduct(ProductDialogDTO productDialogDTO);

        String modifyProduct(ProductDialogDTO productDialogDTO, ProductLocal productLocal);
    }
}
