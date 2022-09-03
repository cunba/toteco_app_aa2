package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.LoginContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;

import java.util.List;

public class LoginModel implements LoginContract.Model {

    private final AppDatabase db;

    public LoginModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Override
    public void createAuxEstablishment() {
        List<EstablishmentLocal> establishments = db.establishmentDao().findAll();
        if (establishments.size() == 0) {
            EstablishmentLocal establishment = new EstablishmentLocal(
//                    "", 0, 0, true, 0
            );
            db.establishmentDao().insert(establishment);
        }
    }

    @Override
    public void createAuxPublication() {
        List<PublicationLocal> publicationLocals = db.publicationDao().findAll();
        if (publicationLocals.size() == 0) {
            List<UserLocal> userLocals = db.userDao().findAll();
            if (userLocals.size() != 0) {
                PublicationLocal publicationLocal = new PublicationLocal(
//                        0, 0, 1, 1
                );
                db.publicationDao().insert(publicationLocal);
            }
        }
    }

    @Override
    public List<UserLocal> getUser(String username, String password) {
        return db.userDao().findByUsernameAndPassword(username, password);
    }

}
