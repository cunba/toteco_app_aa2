package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int id;
    private String date;
    private boolean inMenu;
    private float price;
    private float punctuation;

    private ProductType type;
    private Menu menu;
    private Publication publication;

    protected Product(Parcel in) {
        id = in.readInt();
        date = in.readString();
        inMenu = in.readByte() != 0;
        price = in.readFloat();
        punctuation = in.readFloat();
        type = in.readParcelable(ProductType.class.getClassLoader());
        menu = in.readParcelable(Menu.class.getClassLoader());
        publication = in.readParcelable(Publication.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeByte((byte) (inMenu ? 1 : 0));
        parcel.writeFloat(price);
        parcel.writeFloat(punctuation);
        parcel.writeParcelable(menu, i);
        parcel.writeParcelable(publication, i);
    }

    public Product(String date, boolean inMenu, float price, float punctuation, ProductType type, Menu menu) {
        this.date = date;
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
        this.type = type;
        this.menu = menu;
    }

    public Product(int id, String date, boolean inMenu, float price, float punctuation, ProductType type, Menu menu) {
        this.id = id;
        this.date = date;
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
        this.type = type;
        this.menu = menu;
    }

    public Product() {
    }

    public Product(String date, boolean inMenu, float price, float punctuation) {
        this.date = date;
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
    }

    public Product(int id, String date, boolean inMenu, float price, float punctuation) {
        this.id = id;
        this.date = date;
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
    }

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

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "- " + type.getType() + " " + type.getProductName() +
                "\n(" + price + "€, " + punctuation + "/5★)";
    }
}
