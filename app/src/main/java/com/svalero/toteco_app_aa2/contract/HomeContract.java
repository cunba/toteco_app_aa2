package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.Publication;

import java.util.List;

public interface HomeContract {

    interface Model {
        interface LoadPublicationsListener {
            void loadPublicationsSuccess(List<Publication> publications);

            void loadPublicationsError(String error);
        }

        void loadPublications(LoadPublicationsListener listener);

        void deleteUnsavedLocalProducts();
    }

    interface View {
        void loadPublications(List<Publication> publications);

        void refreshPublications();

        void showToast(String message);
    }

    interface Presenter {
        void loadPublications();

        void deleteUnsavedProducts();
    }
}
