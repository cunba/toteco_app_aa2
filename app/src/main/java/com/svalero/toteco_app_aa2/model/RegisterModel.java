package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.RegisterContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;

import java.util.List;

public class RegisterModel implements RegisterContract.Model {

    private final AppDatabase db;

    public RegisterModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Override
    public void register(UserLocal userLocal) {
        db.userDao().insert(userLocal);
    }

    @Override
    public List<UserLocal> getUser(String username) {
        return db.userDao().findByUsername(username);
    }
}
