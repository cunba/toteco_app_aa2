package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.Instant;
import java.time.ZoneId;

public class User implements Parcelable {
    private int id;
    private String username;
    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String password;
    private String creationDate;
    private boolean active;
    private float moneySpent;
    private int publicationsNumber;
    private String role;

    public User(int id, String username, String name, String surname, String birthDate, String email,
                String password, boolean active, float moneySpent, int publicationsNumber, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.active = active;
        this.moneySpent = moneySpent;
        this.publicationsNumber = publicationsNumber;
        this.role = role;
    }

    public User(String username, String name, String surname, String birthDate, String email, String password,
                boolean active, float moneySpent, int publicationsNumber, String role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.active = active;
        this.moneySpent = moneySpent;
        this.publicationsNumber = publicationsNumber;
        this.role = role;
    }

    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        name = in.readString();
        surname = in.readString();
        birthDate = in.readString();
        email = in.readString();
        password = in.readString();
        creationDate = in.readString();
        active = in.readByte() != 0;
        moneySpent = in.readFloat();
        publicationsNumber = in.readInt();
        role = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", active=" + active +
                ", moneySpent=" + moneySpent +
                ", publicationsNumber=" + publicationsNumber +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(birthDate);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(creationDate);
        parcel.writeByte((byte) (active ? 1 : 0));
        parcel.writeFloat(moneySpent);
        parcel.writeInt(publicationsNumber);
        parcel.writeString(role);
    }
}
