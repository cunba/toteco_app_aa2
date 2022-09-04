package com.svalero.toteco_app_aa2.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;

public class ProductDTO implements Parcelable {
    private boolean inMenu;
    private float price;
    private float punctuation;
    private int typeId;
    private int menuId;
    private int publicationId;

    public ProductDTO(boolean inMenu, float price, float punctuation, int typeId, int menuId, int publicationId) {
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
        this.typeId = typeId;
        this.menuId = menuId;
        this.publicationId = publicationId;
    }

    protected ProductDTO(Parcel in) {
        inMenu = in.readByte() != 0;
        price = in.readFloat();
        punctuation = in.readFloat();
        typeId = in.readInt();
        menuId = in.readInt();
        publicationId = in.readInt();
    }

    public ProductDTO(ProductLocal product) {
        this.inMenu = product.isInMenu();
        this.price = product.getPrice();
        this.punctuation = product.getPunctuation();
        this.typeId = product.getTypeId();
    }

    public static final Creator<ProductDTO> CREATOR = new Creator<ProductDTO>() {
        @Override
        public ProductDTO createFromParcel(Parcel in) {
            return new ProductDTO(in);
        }

        @Override
        public ProductDTO[] newArray(int size) {
            return new ProductDTO[size];
        }
    };

    public boolean isInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (inMenu ? 1 : 0));
        parcel.writeFloat(price);
        parcel.writeFloat(punctuation);
        parcel.writeInt(typeId);
        parcel.writeInt(menuId);
        parcel.writeInt(publicationId);
    }
}
