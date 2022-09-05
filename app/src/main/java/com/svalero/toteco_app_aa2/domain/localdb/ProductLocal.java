package com.svalero.toteco_app_aa2.domain.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.svalero.toteco_app_aa2.domain.Product;

@Entity(tableName = "products")
public class ProductLocal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
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

    @Ignore
    public ProductLocal(int id, String name, boolean inMenu, float price, float punctuation, int typeId, int menuId) {
        this.id = id;
        this.name = name;
        this.inMenu = inMenu;
        this.price = price;
        this.punctuation = punctuation;
        this.typeId = typeId;
        this.menuId = menuId;
    }

    @Ignore
    public ProductLocal(Product product) {
        this.id = product.getId();
        this.name = product.getType().getType() + product.getType().getProductName();
        this.inMenu = product.isInMenu();
        this.price = product.getPrice();
        this.punctuation = product.getPunctuation();
        this.typeId = product.getType().getId();
        this.menuId = product.getMenu().getId();
        this.publicationId = product.getPublication().getId();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "- " + name +
                "\n(" + price + "€, " + punctuation + "/5★)";
    }
}
