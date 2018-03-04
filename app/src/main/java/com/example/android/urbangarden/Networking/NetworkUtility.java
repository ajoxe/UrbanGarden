package com.example.android.urbangarden.Networking;

import android.util.Log;

import com.example.android.urbangarden.model.Garden;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class NetworkUtility {
    private static NetworkUtility utility;
    private static String app_token ="ZSBZKK1xYvwpBLaiSIj4z8vbe";
    private static HashMap<String, String> queryMap;

    public static NetworkUtility getUtility() {
        if (utility == null) {
            utility = new NetworkUtility();
        }
        return utility;
    }

    public void getGardensByQuery(String searchQuery, String queryType, final RetrofitListener listener) {

        queryMap = new HashMap<>();
        queryMap.put("$$app_token", app_token);
        queryMap.put(queryType, searchQuery);
        Log.d("retrofit response", "query type & search " + queryType + " " + searchQuery);
        Call<Garden[]> getCommunityGardens = RetroFitInstance.getInstance()
                .getApi()
                .getGardenData(queryMap);

        getCommunityGardens.enqueue(new Callback<Garden[]>() {
            @Override
            public void onResponse(Call<Garden[]> call, Response<Garden[]> response) {


                Garden[] gardenArray = response.body();
                Log.d("retrofit response", "response length" + response.body().length);
                listener.updateUI(gardenArray);
                Log.d("retrofit response", String.valueOf(gardenArray.length));
            }

            @Override
            public void onFailure(Call<Garden[]> call, Throwable t) {
                listener.onFailureAlert();

            }
        });

    }


}
