package com.svalero.toteco_app_aa2.domain.dto;

public class ProductDialogDTO {
    private String name;
    private String price;
    private String punctuation;

    public ProductDialogDTO(String name, String price, String punctuation) {
        this.name = name;
        this.price = price;
        this.punctuation = punctuation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
