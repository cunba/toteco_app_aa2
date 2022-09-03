package com.svalero.toteco_app_aa2.domain.dto;

import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;

import java.util.List;

public class PublicationToRecyclerView {
    private int publicationId;
    private String establishmentName;
    private String establishmentPunctuation;
    private byte[] image;
    private List<ProductLocal> productLocals;
    private String totalPrice;
    private String totalPunctuation;

    public PublicationToRecyclerView(int publicationId, String establishmentName, String establishmentPunctuation, byte[] image, List<ProductLocal> productLocals, String totalPrice, String totalPunctuation) {
        this.publicationId = publicationId;
        this.establishmentName = establishmentName;
        this.establishmentPunctuation = establishmentPunctuation;
        this.image = image;
        this.productLocals = productLocals;
        this.totalPrice = totalPrice;
        this.totalPunctuation = totalPunctuation;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public List<ProductLocal> getProducts() {
        return productLocals;
    }

    public void setProducts(List<ProductLocal> productLocals) {
        this.productLocals = productLocals;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalPunctuation() {
        return totalPunctuation;
    }

    public void setTotalPunctuation(String totalPunctuation) {
        this.totalPunctuation = totalPunctuation;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEstablishmentPunctuation() {
        return establishmentPunctuation;
    }

    public void setEstablishmentPunctuation(String establishmentPunctuation) {
        this.establishmentPunctuation = establishmentPunctuation;
    }
}
