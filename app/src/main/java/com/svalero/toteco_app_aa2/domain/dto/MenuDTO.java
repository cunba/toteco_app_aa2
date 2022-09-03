package com.svalero.toteco_app_aa2.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuDTO implements Parcelable {
    private float price;
    private float punctuation;

    public MenuDTO(float price, float punctuation) {
        this.price = price;
        this.punctuation = punctuation;
    }

    protected MenuDTO(Parcel in) {
        price = in.readFloat();
        punctuation = in.readFloat();
    }

    public static final Creator<MenuDTO> CREATOR = new Creator<MenuDTO>() {
        @Override
        public MenuDTO createFromParcel(Parcel in) {
            return new MenuDTO(in);
        }

        @Override
        public MenuDTO[] newArray(int size) {
            return new MenuDTO[size];
        }
    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(price);
        parcel.writeFloat(punctuation);
    }
}
