package com.svalero.toteco_app_aa2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.toteco_app_aa2.domain.Establishment;

import java.util.List;

@Dao
public interface EstablishmentDao {
    @Query("SELECT * FROM establishments WHERE name = :name")
    List<Establishment> findByName(String name);

    @Query("SELECT * FROM establishments WHERE name = :name AND id != 1")
    List<Establishment> findByNameExceptAux(String name);

    @Query("SELECT * FROM establishments WHERE id = :id")
    Establishment findById(int id);

    @Query("SELECT * FROM establishments")
    List<Establishment> findAll();

    @Query("SELECT * FROM establishments WHERE id != 1")
    List<Establishment> findAllExceptAux();

    @Query("SELECT * FROM establishments ORDER BY id DESC LIMIT 1")
    Establishment findLast();

    @Insert
    void insert(Establishment establishment);

    @Update
    void update(Establishment establishment);

    @Delete
    void delete(Establishment establishment);

    @Query(value = "SELECT SUM(total_punctuation) FROM publications WHERE establishment_id = :id")
    float sumPunctuation(int id);

    @Query(value = "SELECT COUNT(id) FROM publications WHERE establishment_id = :id")
    int countPublicationsByEstablishmentId(int id);
}
