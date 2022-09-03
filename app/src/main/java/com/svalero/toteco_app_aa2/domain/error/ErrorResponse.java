package com.svalero.toteco_app_aa2.domain.error;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class ErrorResponse {

    private String code;
    private Map<String, String> errors;
    private String message;

    public ErrorResponse(String code, Map<String, String> errors, String message) {
        this.code = code;
        this.errors = errors;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\n' +
                ", errors=" + errors + + '\n' +
                ", message='" + message + '\'' +
                '}';
    }
}
