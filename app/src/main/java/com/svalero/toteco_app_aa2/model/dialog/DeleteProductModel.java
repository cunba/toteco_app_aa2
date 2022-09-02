package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.dialog.DeleteProductContract;
import com.svalero.toteco_app_aa2.contract.dialog.DeletePublicationContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.Publication;

public class DeleteProductModel implements DeleteProductContract.Model {
    private AppDatabase db;

    public DeleteProductModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void delete(Product product) {
        db.productDao().delete(product);
    }
}
