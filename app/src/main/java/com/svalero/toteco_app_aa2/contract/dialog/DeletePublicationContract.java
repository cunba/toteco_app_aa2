package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.Publication;

public interface DeletePublicationContract {
    interface Model {
        void delete(Publication publication);
    }
    interface View {

    }
    interface Presenter {
        void delete(Publication publication);
    }
}
