package com.svalero.toteco_app_aa2.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductTypeDTO implements Parcelable {
    private String type;
    private String product_name;

    public ProductTypeDTO(String type, String product_name) {
        this.type = type;
        this.product_name = product_name;
    }

    protected ProductTypeDTO(Parcel in) {
        type = in.readString();
        product_name = in.readString();
    }

    public static final Creator<ProductTypeDTO> CREATOR = new Creator<ProductTypeDTO>() {
        @Override
        public ProductTypeDTO createFromParcel(Parcel in) {
            return new ProductTypeDTO(in);
        }

        @Override
        public ProductTypeDTO[] newArray(int size) {
            return new ProductTypeDTO[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(product_name);
    }
}
