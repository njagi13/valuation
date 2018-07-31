package com.dnjagi.carval.data;

import com.dnjagi.carval.global.GlobalVarible;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiClient {
    static GlobalVarible globalVars = new GlobalVarible();

    public static final String BASE_URL = globalVars.url;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
