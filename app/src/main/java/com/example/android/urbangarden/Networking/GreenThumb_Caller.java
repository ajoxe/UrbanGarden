package com.example.android.urbangarden.Networking;

import com.example.android.urbangarden.model.GardenData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bobbybah on 3/3/18.
 */

public interface GreenThumb_Caller {

    String baseURL = "https://data.cityofnewyork.us/";

    @GET("resource/yes4-7zbb.json")
    Call<GardenData> getGardenData(@Query("apitoken") String apitoken);
}
