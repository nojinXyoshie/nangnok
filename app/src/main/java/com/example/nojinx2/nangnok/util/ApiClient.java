package com.example.nojinx2.nangnok.util;

//import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://www.nojinxyoshie.epizy.com/API/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
