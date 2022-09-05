package com.svalero.toteco_app_aa2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.toteco_app_aa2.dao.EstablishmentDao;
import com.svalero.toteco_app_aa2.dao.ProductDao;
import com.svalero.toteco_app_aa2.dao.PublicationDao;
import com.svalero.toteco_app_aa2.dao.UserDao;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.MenuLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductTypeLocal;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;

@Database(entities = {EstablishmentLocal.class, ProductLocal.class,
        PublicationLocal.class, UserLocal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EstablishmentDao establishmentDao();

    public abstract ProductDao productDao();

    public abstract PublicationDao publicationDao();

    public abstract UserDao userDao();
}
