package com.svalero.toteco_app_aa2.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.svalero.toteco_app_aa2.domain.error.ErrorResponse;

import java.io.Reader;
import java.lang.reflect.Type;

public class Utils {
    public static double roundNumber(float number) {
        return Math.round(number * 100.0) / 100.0;
    }

    public static double roundNumber(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    public static String getErrorResponse(Reader response) {
        Gson gson = new Gson();
        Type type = new TypeToken<ErrorResponse>() {
        }.getType();
        ErrorResponse errorResponse = gson.fromJson(response, type);
        if (errorResponse.getMessage().equals("Validation error")) {
            StringBuilder error = new StringBuilder();
            for (String key : errorResponse.getErrors().keySet()) {
                error.append("El campo ").append(key).append(' ').append(errorResponse.getErrors().get(key)).append(".\n");
            }
            return error.toString();
        }
        return errorResponse.getMessage();
    }
}
