package com.svalero.toteco_app_aa2.contract.dialog;

import com.google.android.gms.maps.model.Marker;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.dto.view.AddEstablishmentDTO;

import java.util.List;

public interface AddEstablishmentContract {
    interface Model {
        interface LoadEstablishmentsListener {
            void loadEstablishmentsSuccess(List<Establishment> establishments);
            void loadEstablishmentsError(String error);
        }
        void loadEstablishments(LoadEstablishmentsListener listener);
        String onPressSubmit(AddEstablishmentDTO addEstablishmentDTO);
        Establishment findEstablishment(List<Establishment> establishments, Marker marker);
    }
    interface View {
        void loadEstablishments(List<Establishment> establishments);
        void onPressSubmit(android.view.View view);
        void onSubmit(String error);
    }
    interface Presenter {
        void loadEstablishments();
        Establishment findEstablishment(List<Establishment> establishments, Marker marker);
        void onPressSubmit(AddEstablishmentDTO addEstablishmentDTO);
    }
}
