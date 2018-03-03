package com.example.android.urbangarden.Networking;

import com.example.android.urbangarden.model.GardenData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public interface RetrofitService {
    String baseURL = "https://data.cityofnewyork.us/";

    @GET("resource/yes4-7zbb.json")
    Call<GardenData> getGardenData(@Query("apiToken") String apiToken);
}
