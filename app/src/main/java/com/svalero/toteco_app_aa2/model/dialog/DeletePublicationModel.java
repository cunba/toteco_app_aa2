package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.dialog.DeletePublicationContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;

public class DeletePublicationModel implements DeletePublicationContract.Model {
    private AppDatabase db;

    public DeletePublicationModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void delete(PublicationLocal publicationLocal) {
        db.publicationDao().delete(publicationLocal);
    }
}
