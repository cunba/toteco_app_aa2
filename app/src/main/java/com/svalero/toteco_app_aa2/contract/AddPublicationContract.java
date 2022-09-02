package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationDTO;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationSummaryDTO;

import java.util.List;

public interface AddPublicationContract {

    interface Model {
        List<Product> loadProducts();
        AddPublicationSummaryDTO makeSummary(double establishmentPunctuation);
        void onPressSubmit(AddPublicationDTO addPublicationDTO);
        Establishment clearEstablishmentAux();
        Establishment getEstablishment();
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
        List<Product> loadProducts();
        AddPublicationSummaryDTO makeSummary(double establishmentPunctuation);
        void onPressSubmit(AddPublicationDTO publicationDTO);
        Establishment clearEstablishmentAux();
        Establishment getEstablishment();
    }
}
