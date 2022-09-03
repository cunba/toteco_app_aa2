package com.svalero.toteco_app_aa2.domain.dto;

import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;

public class AddEstablishmentDTO {
    private String name;
    private String Punctuation;
    private EstablishmentLocal establishment;

    public AddEstablishmentDTO(String name, String punctuation, EstablishmentLocal establishment) {
        this.name = name;
        Punctuation = punctuation;
        this.establishment = establishment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPunctuation() {
        return Punctuation;
    }

    public void setPunctuation(String punctuation) {
        Punctuation = punctuation;
    }

    public EstablishmentLocal getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentLocal establishment) {
        this.establishment = establishment;
    }
}
