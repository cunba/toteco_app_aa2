package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;

import java.util.List;

public interface LocationsContract {

    interface Model {
        List<EstablishmentLocal> loadEstablishments();
    }

    interface View {
        void loadEstablishments();
    }

    interface Presenter {
        List<EstablishmentLocal> loadEstablishments();
    }
}
