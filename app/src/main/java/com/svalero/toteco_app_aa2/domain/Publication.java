package com.svalero.toteco_app_aa2.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Publication implements Parcelable {
    private int id;
    private String date;
    private float totalPrice;
    private float totalPunctuation;
    private String photo;

    private User user;
    private Establishment establishment;

    private List<Product> products;

    public Publication(int id, String date, float totalPrice, float totalPunctuation, String photo, User user, Establishment establishment) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalPunctuation = totalPunctuation;
        this.photo = photo;
        this.user = user;
        this.establishment = establishment;
    }

    public Publication(String date, float totalPrice, float totalPunctuation, String photo, User user, Establishment establishment) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalPunctuation = totalPunctuation;
        this.photo = photo;
        this.user = user;
        this.establishment = establishment;
    }

    public Publication(int id, String date, float totalPrice, float totalPunctuation, String photo) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalPunctuation = totalPunctuation;
        this.photo = photo;
    }

    public Publication(String date, float totalPrice, float totalPunctuation, String photo) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalPunctuation = totalPunctuation;
        this.photo = photo;
    }

    public Publication() {
    }

    protected Publication(Parcel in) {
        id = in.readInt();
        date = in.readString();
        totalPrice = in.readFloat();
        totalPunctuation = in.readFloat();
        photo = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        establishment = in.readParcelable(Establishment.class.getClassLoader());
        products = in.readParcelableList(products, Product.class.getClassLoader());
    }

    public static final Creator<Publication> CREATOR = new Creator<Publication>() {
        @Override
        public Publication createFromParcel(Parcel in) {
            return new Publication(in);
        }

        @Override
        public Publication[] newArray(int size) {
            return new Publication[size];
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getTotalPunctuation() {
        return totalPunctuation;
    }

    public void setTotalPunctuation(float totalPunctuation) {
        this.totalPunctuation = totalPunctuation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", totalPunctuation=" + totalPunctuation +
                ", photo='" + photo + '\'' +
                ", user=" + user +
                ", establishment=" + establishment +
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
        parcel.writeFloat(totalPrice);
        parcel.writeFloat(totalPunctuation);
        parcel.writeString(photo);
        parcel.writeParcelable(user, i);
        parcel.writeParcelable(establishment, i);
        parcel.writeParcelableList(products, i);
    }
}
