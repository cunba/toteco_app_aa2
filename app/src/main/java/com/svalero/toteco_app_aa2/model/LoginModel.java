package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.LoginContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.User;

import java.util.List;

public class LoginModel implements LoginContract.Model {

    private final AppDatabase db;

    public LoginModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Override
    public void createAuxEstablishment() {
        List<Establishment> establishments = db.establishmentDao().findAll();
        if (establishments.size() == 0) {
            Establishment establishment = new Establishment("", 0, 0, true, 0);
            db.establishmentDao().insert(establishment);
        }
    }

    @Override
    public void createAuxPublication() {
        List<Publication> publications = db.publicationDao().findAll();
        if (publications.size() == 0) {
            List<User> users = db.userDao().findAll();
            if (users.size() != 0) {
                Publication publication = new Publication(0, 0, 1, 1);
                db.publicationDao().insert(publication);
            }
        }
    }

    @Override
    public List<User> getUser(String username, String password) {
        return db.userDao().findByUsernameAndPassword(username, password);
    }

}
