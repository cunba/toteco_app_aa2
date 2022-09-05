package com.svalero.toteco_app_aa2.contract.dialog;

import com.svalero.toteco_app_aa2.domain.Publication;

public interface AddFavouritesContract {
    interface Model {
        void addFavourite(Publication publication);
    }

    interface View {

    }

    interface Presenter {
        void addFavourite(Publication publication);
    }
}
