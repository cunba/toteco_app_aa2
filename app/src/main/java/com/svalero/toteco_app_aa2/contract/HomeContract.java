package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.dto.PublicationToRecyclerView;

import java.util.List;

public interface HomeContract {

    interface Model {
        List<Publication> loadPublications();
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
        List<Publication> loadPublications();
        List<PublicationToRecyclerView> convertPublications();
        void deleteUnsavedProducts();
    }
}
