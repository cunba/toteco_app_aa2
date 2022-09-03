package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;

public interface DeletePublicationContract {
    interface Model {
        void delete(PublicationLocal publicationLocal);
    }
    interface View {

    }
    interface Presenter {
        void delete(PublicationLocal publicationLocal);
    }
}
