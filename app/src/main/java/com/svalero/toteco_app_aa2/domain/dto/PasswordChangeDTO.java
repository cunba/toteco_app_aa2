package com.svalero.toteco_app_aa2.domain.dto;

public class PasswordChangeDTO {
    private String password;

    public PasswordChangeDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
