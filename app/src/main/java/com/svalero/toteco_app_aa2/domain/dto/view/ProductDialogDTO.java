package com.svalero.toteco_app_aa2.domain.dto.view;

import com.svalero.toteco_app_aa2.domain.ProductType;

public class ProductDialogDTO {
    private ProductType type;
    private String price;
    private String punctuation;

    public ProductDialogDTO(ProductType type, String price, String punctuation) {
        this.type = type;
        this.price = price;
        this.punctuation = punctuation;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(String punctuation) {
        this.punctuation = punctuation;
    }
}
