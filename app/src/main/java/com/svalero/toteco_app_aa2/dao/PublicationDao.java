package com.svalero.toteco_app_aa2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;

import java.util.List;

@Dao
public interface PublicationDao {

    @Query("SELECT * FROM publications WHERE id = :id")
    PublicationLocal findById(int id);

    @Query("SELECT * FROM publications")
    List<PublicationLocal> findAll();

    @Query("SELECT * FROM publications WHERE id != 1 ORDER BY id DESC")
    List<PublicationLocal> findAllExceptAux();

    @Query("SELECT * FROM publications ORDER BY id DESC LIMIT 1")
    PublicationLocal findLast();

    @Query("SELECT id FROM publications ORDER BY id DESC LIMIT 1")
    int findLastId();

    @Insert
    void insert(PublicationLocal publicationLocal);

    @Update
    void update(PublicationLocal publicationLocal);

    @Delete
    void delete(PublicationLocal publicationLocal);

    @Query("SELECT SUM(price) AS suma FROM products WHERE publication_id = :id")
    float totalPrice(int id);

    @Query("SELECT SUM(suma)/SUM(num) FROM (SELECT SUM(punctuation) AS suma, COUNT(id) AS num FROM products WHERE publication_id = :id)")
    float totalPunctuation(int id);
}
