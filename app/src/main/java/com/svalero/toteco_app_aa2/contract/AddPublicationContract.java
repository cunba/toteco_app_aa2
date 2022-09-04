package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.dto.EstablishmentDTO;
import com.svalero.toteco_app_aa2.domain.dto.PublicationDTO;
import com.svalero.toteco_app_aa2.domain.dto.view.AddPublicationSummaryDTO;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;

import java.util.List;

public interface AddPublicationContract {

    interface Model {
        List<ProductLocal> loadProducts();

        AddPublicationSummaryDTO makeSummary(double establishmentPunctuation);

        EstablishmentLocal getEstablishment();

        interface CreateEstablishmentListener {
            void createEstablishmentSuccess(Establishment establishment, byte[] image);

            void createEstablishmentError(String error);
        }

        void createEstablishment(CreateEstablishmentListener listener, EstablishmentDTO establishmentDTO, byte[] image);

        void onPressSubmit(byte[] image);

        interface CreatePublicationListener {
            void createPublicationSuccess(Publication publication);

            void createPublicationError(String error);
        }

        void createPublication(CreatePublicationListener listener, PublicationDTO publicationDTO);

        interface CreateProductsListener {
            void createProductsSuccess();

            void createProductsError(String error);
        }

        void createProducts(CreateProductsListener listener);

        interface UpdatePublicationPricePunctuationListener {
            void updatePublicationPricePunctuationSuccess();

            void updatePublicationPricePunctuationError(String error);
        }

        void updatePublicationPricePunctuation(UpdatePublicationPricePunctuationListener listener);

        interface UpdateEstablishmentPunctuationListener {
            void updateEstablishmentPunctuationSuccess();

            void updateEstablishmentPunctuationError(String error);
        }

        void updateEstablishmentPunctuation(UpdateEstablishmentPunctuationListener listener);
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

        void onSubmit(String message);
    }

    interface Presenter {
        List<ProductLocal> loadProducts();

        AddPublicationSummaryDTO makeSummary(double establishmentPunctuation);

        void onPressSubmit(byte[] image);

        EstablishmentLocal getEstablishment();

        void onSubmit(String message);
    }
}
