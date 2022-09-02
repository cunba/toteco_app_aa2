package com.svalero.toteco_app_aa2.domain.dto;

import com.svalero.toteco_app_aa2.domain.Establishment;

public class AddPublicationDTO {
    private Establishment establishment;
    private String establishmentPunctuation;
    private byte[] image;

    public AddPublicationDTO(Establishment establishment, String establishmentPunctuation, byte[] image) {
        this.establishment = establishment;
        this.establishmentPunctuation = establishmentPunctuation;
        this.image = image;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
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
