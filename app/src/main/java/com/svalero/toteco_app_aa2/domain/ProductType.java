package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductType implements Parcelable {
    private int id;
    private String productName;
    private String type;

    protected ProductType(Parcel in) {
        id = in.readInt();
        productName = in.readString();
        type = in.readString();
    }

    public static final Creator<ProductType> CREATOR = new Creator<ProductType>() {
        @Override
        public ProductType createFromParcel(Parcel in) {
            return new ProductType(in);
        }

        @Override
        public ProductType[] newArray(int size) {
            return new ProductType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(productName);
        parcel.writeString(type);
    }

    public ProductType(int id, String productName, String type) {
        this.id = id;
        this.productName = productName;
        this.type = type;
    }
    public ProductType(String productName, String type) {
        this.productName = productName;
        this.type = type;
    }
    public ProductType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return productName + " " + type;
    }
}
