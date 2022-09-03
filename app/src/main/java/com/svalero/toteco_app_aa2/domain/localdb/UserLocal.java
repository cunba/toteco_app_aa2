package com.svalero.toteco_app_aa2.domain.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.svalero.toteco_app_aa2.domain.User;

import java.time.format.DateTimeFormatter;

@Entity(tableName = "users")
public class UserLocal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String surname;
    @ColumnInfo(name = "birth_date")
    private String birthDate;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String password;
    @ColumnInfo(name = "creation_date")
    private String creationDate;
    @ColumnInfo
    private boolean active;
    @ColumnInfo(name = "money_spent")
    private float moneySpent;
    @ColumnInfo(name = "publications_number")
    private int publicationsNumber;
    @ColumnInfo
    private String role;
    @ColumnInfo
    private String token;

    public UserLocal(int id, String username, String name, String surname, String birthDate,
                     String email, String password, String creationDate, boolean active,
                     float moneySpent, int publicationsNumber, String role, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.active = active;
        this.moneySpent = moneySpent;
        this.publicationsNumber = publicationsNumber;
        this.role = role;
        this.token = token;
    }

    public UserLocal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.birthDate = user.getBirthDate();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.creationDate = user.getCreationDate();
        this.active = user.isActive();
        this.moneySpent = user.getMoneySpent();
        this.publicationsNumber = user.getPublicationsNumber();
        this.role = user.getRole();
    }

    public UserLocal(String username, String name, String surname, String birthDate, String email,
                     String password, String creationDate, boolean active, float moneySpent,
                     int publicationsNumber, String role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.active = active;
        this.moneySpent = moneySpent;
        this.publicationsNumber = publicationsNumber;
        this.role = role;
    }

    public UserLocal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(float moneySpent) {
        this.moneySpent = moneySpent;
    }

    public int getPublicationsNumber() {
        return publicationsNumber;
    }

    public void setPublicationsNumber(int publicationsNumber) {
        this.publicationsNumber = publicationsNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
