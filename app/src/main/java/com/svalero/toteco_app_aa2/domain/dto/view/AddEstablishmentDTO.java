package com.svalero.toteco_app_aa2.domain.dto.view;

import com.svalero.toteco_app_aa2.domain.Establishment;

public class AddEstablishmentDTO {
    private String name;
    private String Punctuation;
    private Establishment establishment;

    public AddEstablishmentDTO(String name, String punctuation, Establishment establishment) {
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

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
