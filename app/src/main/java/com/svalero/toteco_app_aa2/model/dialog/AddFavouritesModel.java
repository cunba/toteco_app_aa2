package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.dialog.AddFavouritesContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;

public class AddFavouritesModel implements AddFavouritesContract.Model {

    private AppDatabase db;

    public AddFavouritesModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void addFavourite(Publication publication) {
        PublicationLocal publicationLocal = new PublicationLocal(publication);
        db.publicationDao().insert(publicationLocal);
    }
}
