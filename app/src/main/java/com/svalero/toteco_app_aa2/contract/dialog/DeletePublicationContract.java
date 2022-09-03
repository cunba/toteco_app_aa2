package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.Publication;

public interface DeletePublicationContract {
    interface Model {
        interface DeleteListener {
            void deleteSuccess(String message);

            void deleteError(String message);
        }

        void delete(DeleteListener listener, Publication publication);
    }

    interface View {
        void onDelete(String message);
    }

    interface Presenter {
        void delete(Publication publication);
    }
}
