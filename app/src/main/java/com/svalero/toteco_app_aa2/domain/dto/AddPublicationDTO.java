package com.svalero.toteco_app_aa2.domain.dto;

import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;

public class AddPublicationDTO {
    private EstablishmentLocal establishment;
    private String establishmentPunctuation;
    private String image;

    public AddPublicationDTO(EstablishmentLocal establishment, String establishmentPunctuation, String image) {
        this.establishment = establishment;
        this.establishmentPunctuation = establishmentPunctuation;
        this.image = image;
    }

    public EstablishmentLocal getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentLocal establishment) {
        this.establishment = establishment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEstablishmentPunctuation() {
        return establishmentPunctuation;
    }

    public void setEstablishmentPunctuation(String establishmentPunctuation) {
        this.establishmentPunctuation = establishmentPunctuation;
    }
}
