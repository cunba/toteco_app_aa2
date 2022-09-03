package com.svalero.toteco_app_aa2.domain.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.svalero.toteco_app_aa2.domain.Publication;

import java.util.Arrays;

@Entity(tableName = "publications")
public class PublicationLocal {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo
    private String date;
    @ColumnInfo(name = "total_price")
    private float totalPrice;
    @ColumnInfo(name = "total_punctuation")
    private float totalPunctuation;
    @ColumnInfo
    private String photo;
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "establishment_id")
    private int establishmentId;

    public PublicationLocal(String date, float totalPrice, float totalPunctuation, int userId, int establishmentId) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalPunctuation = totalPunctuation;
        this.userId = userId;
        this.establishmentId = establishmentId;
    }

    public PublicationLocal(Publication publication) {
        this.id = publication.getId();
        this.date = publication.getDate();
        this.totalPrice = publication.getTotalPrice();
        this.totalPunctuation = publication.getTotalPunctuation();
        this.photo = publication.getPhoto();
        this.userId = publication.getUser().getId();
        this.establishmentId = publication.getEstablishment().getId();
    }

    public PublicationLocal() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PublicationLocal{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", totalPunctuation=" + totalPunctuation +
                ", userId=" + userId +
                ", establishmentId=" + establishmentId +
                '}';
    }
}
