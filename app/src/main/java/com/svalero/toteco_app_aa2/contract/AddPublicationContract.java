package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.dto.AddPublicationDTO;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationSummaryDTO;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;

import java.util.List;

public interface AddPublicationContract {

    interface Model {
        List<ProductLocal> loadProducts();
        AddPublicationSummaryDTO makeSummary(double establishmentPunctuation);
        void onPressSubmit(AddPublicationDTO addPublicationDTO);
        EstablishmentLocal clearEstablishmentAux();
        EstablishmentLocal getEstablishment();
    }

    interface View {
        void initializeProducts();
        void setEstablishment();
        void refreshProductsList();
        void makeSummary();
        void onPressAddProduct();
        void onPressAddEstablishment();
        void onPressAddImage();
        void onPressSubmit();
        void loadProducts();
        void showError(String message);
        void showToast(String message);
    }

    interface Presenter {
        List<ProductLocal> loadProducts();
        AddPublicationSummaryDTO makeSummary(double establishmentPunctuation);
        void onPressSubmit(AddPublicationDTO publicationDTO);
        EstablishmentLocal clearEstablishmentAux();
        EstablishmentLocal getEstablishment();
    }
}
