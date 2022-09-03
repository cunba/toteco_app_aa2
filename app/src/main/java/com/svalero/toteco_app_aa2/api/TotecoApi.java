package com.svalero.toteco_app_aa2.api;

import static com.svalero.toteco_app_aa2.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TotecoApi {

    public static TotecoApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TotecoApiInterface.class);
    }
}
