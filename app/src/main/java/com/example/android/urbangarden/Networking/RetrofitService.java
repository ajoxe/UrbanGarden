package com.example.android.urbangarden.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class RetrofitService {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(String basUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(basUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
