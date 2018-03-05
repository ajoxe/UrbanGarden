package com.example.android.urbangarden.database;

import android.content.Context;

import com.example.android.urbangarden.R;
import com.example.android.urbangarden.model.Garden;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by amirahoxendine on 3/4/18.
 */

public class DummyDataUtility {
    public List<Garden> gardenList;

    public List<Garden> buildDummyList(Context context){
        Type collectiontype = new TypeToken<Collection<Garden>>() {
        }.getType();
        Gson gs = new Gson();
        Collection<Garden> gardens = null;
        InputStream is = context.getApplicationContext().getResources().openRawResource(R.raw.dummydata);
        InputStreamReader isr = new InputStreamReader(is);
        gardens = gs.fromJson(isr, collectiontype);
        gardenList = new ArrayList<>();
        gardenList.addAll(gardens);

        return gardenList;
    }



    public List<Garden> addSavedGardens(List<Garden> gardenList){
        if(gardenList.size()>6) {
            for (int i = 0; i < 4; i++) {
                gardenList.get(i).setStatus("saved");
            }
        } else{
            for(Garden garden : gardenList){
                garden.setStatus("saved");
            }
        }
        return gardenList;
    }
}
