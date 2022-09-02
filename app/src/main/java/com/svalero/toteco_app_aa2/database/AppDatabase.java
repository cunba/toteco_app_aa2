package com.svalero.toteco_app_aa2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.toteco_app_aa2.dao.EstablishmentDao;
import com.svalero.toteco_app_aa2.dao.ProductDao;
import com.svalero.toteco_app_aa2.dao.PublicationDao;
import com.svalero.toteco_app_aa2.dao.UserDao;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.User;

@Database(entities = {Establishment.class, Product.class, Publication.class, User.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EstablishmentDao establishmentDao();

    public abstract ProductDao productDao();

    public abstract PublicationDao publicationDao();

    public abstract UserDao userDao();
}
