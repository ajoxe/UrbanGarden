package com.example.android.urbangarden.Networking;

import com.example.android.urbangarden.model.Garden;
import com.example.android.urbangarden.model.GardenData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public interface RetrofitGardenService {


    @GET("resource/yes4-7zbb.json")
    Call<Garden[]> getGardenData(@QueryMap Map<String, String> searchOptions);
}
