package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.domain.dto.PublicationToRecyclerView;

import java.util.List;

public interface HomeContract {

    interface Model {
        List<PublicationLocal> loadPublications();
        void deleteUnsavedProducts();
        List<PublicationToRecyclerView> convertPublications();
    }

    interface View {
        void createRecyclerView();
        void convertPublications();
        void loadPublications();
        void refreshPublications();
    }

    interface Presenter {
        List<PublicationLocal> loadPublications();
        List<PublicationToRecyclerView> convertPublications();
        void deleteUnsavedProducts();
    }
}
