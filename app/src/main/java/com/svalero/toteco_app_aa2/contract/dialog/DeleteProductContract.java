package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;

public interface DeleteProductContract {
    interface Model {
        void delete(ProductLocal productLocal);
    }
    interface View {

    }
    interface Presenter {
        void delete(ProductLocal productLocal);
    }
}
