package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    private int id;
    private String date;
    private float price;
    private float punctuation;

    public Menu(String date, float price, float punctuation) {
        this.date = date;
        this.price = price;
        this.punctuation = punctuation;
    }

    public Menu() {
    }

    protected Menu(Parcel in) {
        id = in.readInt();
        date = in.readString();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        parcel.writeString(date);
        parcel.writeFloat(price);
        parcel.writeFloat(punctuation);
    }
}
