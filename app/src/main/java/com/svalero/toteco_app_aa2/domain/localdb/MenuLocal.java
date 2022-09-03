package com.svalero.toteco_app_aa2.domain.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.svalero.toteco_app_aa2.domain.Menu;

@Entity(tableName = "menus")
public class MenuLocal {
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float punctuation;

    public MenuLocal(int id, String date, float price, float punctuation) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.punctuation = punctuation;
    }

    public MenuLocal(String date, float price, float punctuation) {
        this.date = date;
        this.price = price;
        this.punctuation = punctuation;
    }

    public MenuLocal() {
    }

    public MenuLocal(Menu menu) {
        this.id = menu.getId();
        this.date = menu.getDate();
        this.price = menu.getPrice();
        this.punctuation = menu.getPunctuation();
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
