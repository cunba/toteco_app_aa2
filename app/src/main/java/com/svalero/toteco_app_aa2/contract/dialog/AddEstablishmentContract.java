package com.svalero.toteco_app_aa2.contract.dialog;

import com.google.android.gms.maps.model.LatLng;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.dto.AddEstablishmentDTO;

import java.util.List;

public interface AddEstablishmentContract {
    interface Model {
        List<Establishment> loadEstablishments();
        String onPressSubmit(AddEstablishmentDTO addEstablishmentDTO);
        List<Establishment> findByName(String name);
    }
    interface View {
        void loadEstablishments();
        void addEstablishment(LatLng latLng);
        void onPressSubmit(android.view.View view);
    }
    interface Presenter {
        List<Establishment> loadEstablishments();
        String onPressSubmit(AddEstablishmentDTO addEstablishmentDTO);
        List<Establishment> findByName(String name);
    }
}
