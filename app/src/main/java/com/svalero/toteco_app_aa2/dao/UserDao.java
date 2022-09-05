package com.svalero.toteco_app_aa2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<UserLocal> findAll();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    List<UserLocal> findByUsernameAndPassword(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    List<UserLocal> findByUsername(String username);

    @Query("SELECT id FROM users WHERE username = :username")
    List<Integer> findIdByUsername(String username);

    @Insert
    void insert(UserLocal userLocal);

    @Query("UPDATE users SET password = :password WHERE id = :id")
    void updatePassword(int id, String password);

    @Update
    void update(UserLocal userLocal);

    @Delete
    void delete(UserLocal userLocal);
}
