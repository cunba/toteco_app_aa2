package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.RegisterContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.User;

import java.util.List;

public class RegisterModel implements RegisterContract.Model {

    private final AppDatabase db;

    public RegisterModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Override
    public void register(User user) {
        db.userDao().insert(user);
    }

    @Override
    public List<User> getUser(String username) {
        return db.userDao().findByUsername(username);
    }
}
