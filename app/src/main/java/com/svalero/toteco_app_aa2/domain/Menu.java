package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Menu implements Parcelable {
    private int id;
    private LocalDate date;
    private float price;
    private float punctuation;

    public Menu(LocalDate date, float price, float punctuation) {
        this.date = date;
        this.price = price;
        this.punctuation = punctuation;
    }

    public Menu() {
    }

    protected Menu(Parcel in) {
        id = in.readInt();
        date = Instant.ofEpochMilli(in.readLong()).atZone(ZoneId.systemDefault()).toLocalDate();
        price = in.readFloat();
        punctuation = in.readFloat();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(float punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", punctuation=" + punctuation +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeLong(date.toEpochDay());
        parcel.writeFloat(price);
        parcel.writeFloat(punctuation);
    }
}
