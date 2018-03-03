package com.example.android.urbangarden.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class RetroFitInstance {
   private static String base_url = "https://data.cityofnewyork.us/";
    public static RetroFitInstance instance;

    public RetroFitInstance() {

    }

    public static RetroFitInstance getInstance() {
        if (instance == null) {
            instance = new RetroFitInstance();
        }
        return instance;
    }

    Retrofit getRetrofit() { // getting the retofit builder to use in other methods
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitGardenService getApi() {
        return getRetrofit().create(RetrofitGardenService.class);
    }
}
