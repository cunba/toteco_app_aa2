package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.Product;

public interface DeleteProductContract {
    interface Model {
        void delete(Product product);
    }
    interface View {

    }
    interface Presenter {
        void delete(Product product);
    }
}
