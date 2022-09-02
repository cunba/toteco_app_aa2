package com.svalero.toteco_app_aa2.domain.dto;

public class AddPublicationSummaryDTO {
    private double totalPrice;
    private double totalPunctuation;

    public AddPublicationSummaryDTO(double totalPrice, double totalPunctuation) {
        this.totalPrice = totalPrice;
        this.totalPunctuation = totalPunctuation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPunctuation() {
        return totalPunctuation;
    }

    public void setTotalPunctuation(double totalPunctuation) {
        this.totalPunctuation = totalPunctuation;
    }
}
