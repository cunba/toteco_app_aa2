package com.svalero.toteco_app_aa2.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDTO implements Parcelable {
    private String username;
    private String name;
    private String surname;
    private String birth_date;
    private String email;
    private String password;
    private String role;

    public UserDTO(String username, String name, String surname, String birth_date, String email, String password, String role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    protected UserDTO(Parcel in) {
        username = in.readString();
        name = in.readString();
        surname = in.readString();
        birth_date = in.readString();
        email = in.readString();
        password = in.readString();
        role = in.readString();
    }

    public static final Creator<UserDTO> CREATOR = new Creator<UserDTO>() {
        @Override
        public UserDTO createFromParcel(Parcel in) {
            return new UserDTO(in);
        }

        @Override
        public UserDTO[] newArray(int size) {
            return new UserDTO[size];
        }
    };

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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(birth_date);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(role);
    }
}
