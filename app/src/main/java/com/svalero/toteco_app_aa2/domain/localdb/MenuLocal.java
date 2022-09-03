package com.svalero.toteco_app_aa2.domain.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menus")
public class MenuLocal {
    @PrimaryKey
    private int id;
    @ColumnInfo
    private long date;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float punctuation;

    public MenuLocal(int id, long date, float price, float punctuation) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.punctuation = punctuation;
    }

    public MenuLocal(long date, float price, float punctuation) {
        this.date = date;
        this.price = price;
        this.punctuation = punctuation;
    }

    public MenuLocal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
}
