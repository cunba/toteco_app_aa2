package com.svalero.toteco_app_aa2.domain.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductLocal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "in_menu")
    private boolean inMenu;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float punctuation;
    @ColumnInfo(name = "type_id")
    private int typeId;
    @ColumnInfo(name = "menu_id")
    private int menuId;
    @ColumnInfo(name = "publication_id")
    private int publicationId;

    public ProductLocal(int id, boolean inMenu, float price, float punctuation, int typeId, int menuId, int publicationId) {
        this.id = id;
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
        this.typeId = typeId;
        this.menuId = menuId;
        this.publicationId = publicationId;
    }

    public ProductLocal(boolean inMenu, float price, float punctuation, int typeId, int menuId, int publicationId) {
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
        this.typeId = typeId;
        this.menuId = menuId;
        this.publicationId = publicationId;
    }

    public ProductLocal(boolean inMenu, float price, float punctuation) {
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
    }
    
    public ProductLocal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    //    @NonNull
//    @Override
//    public String toString() {
//        return "- " + name + "\n(" + price + "€, " + punctuation + "/5★)";
//    }
}
