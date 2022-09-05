package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.MainContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;

import java.util.List;

public class MainModel implements MainContract.Model {
    private final AppDatabase db;

    public MainModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
    @Override
    public void deleteUser() {
        List<UserLocal> users = db.userDao().findAll();
        users.stream().forEach(u -> db.userDao().delete(u));
    }
}
