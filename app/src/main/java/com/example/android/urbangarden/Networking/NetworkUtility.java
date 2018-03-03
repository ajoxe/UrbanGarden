package com.example.android.urbangarden.Networking;

import android.util.Log;

import com.example.android.urbangarden.model.Garden;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class NetworkUtility {
    private static NetworkUtility utility;
    private static String app_token ="ZSBZKK1xYvwpBLaiSIj4z8vbe";
    private static HashMap<String, String> queryMap = new HashMap<>();

    public static NetworkUtility getUtility() {
        if (utility == null) {
            utility = new NetworkUtility();
        }
        return utility;
    }

    public void getGardensByQuery(String searchQuery, String queryType, final RetrofitListener listener) {
        queryMap.put("$$app_token", app_token);
        queryMap.put(queryType, searchQuery);

        Call<Garden[]> getCommunityGardens = RetroFitInstance.getInstance()
                .getApi()
                .getGardenData(queryMap);

        getCommunityGardens.enqueue(new Callback<Garden[]>() {
            @Override
            public void onResponse(Call<Garden[]> call, Response<Garden[]> response) {
                Log.d("retrofit", "retrofit happened!");
                listener.updateUI(response.body());
            }

            @Override
            public void onFailure(Call<Garden[]> call, Throwable t) {
                Log.d("retrofit", "retrofit didnt happened!");
                listener.onFailureAlert();

            }
        });

    }
}
