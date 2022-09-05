package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;

import java.util.List;

public interface LocationsContract {

    interface Model {
        interface LoadEstablishmentsListener {
            void loadEstablishmentsSuccess(List<Establishment> establishments);

            void loadEstablishmentsError(String error);
        }

        void loadEstablishments(LoadEstablishmentsListener listener);
    }

    interface View {
        void loadEstablishments(List<Establishment> establishments);
        void showToast(String message);
    }

    interface Presenter {
        void loadEstablishments();
    }
}
