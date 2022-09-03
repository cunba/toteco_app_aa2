package com.svalero.toteco_app_aa2.domain.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_types")
public class ProductTypeLocal {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "product_name")
    private String productName;
    @ColumnInfo
    private String type;

    public ProductTypeLocal(int id, String productName, String type) {
        this.id = id;
        this.productName = productName;
        this.type = type;
    }

    public ProductTypeLocal() {
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
}
