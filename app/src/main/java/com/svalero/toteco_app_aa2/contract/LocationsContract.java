package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.Establishment;

import java.util.List;

public interface LocationsContract {

    interface Model {
        List<Establishment> loadEstablishments();
    }

    interface View {
        void loadEstablishments();
    }

    interface Presenter {
        List<Establishment> loadEstablishments();
    }
}
