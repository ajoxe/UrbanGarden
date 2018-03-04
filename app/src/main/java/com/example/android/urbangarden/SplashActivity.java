package com.example.android.urbangarden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.urbangarden.database.DummyDataListener;
import com.example.android.urbangarden.database.DummyDataUtility;
import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;
import com.example.android.urbangarden.model.Garden;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setDummyData();
        Intent intent = new Intent(this,GardenSearchActivity.class);
        startActivity(intent);
        finish();
    }

    public void setDummyData(){
        DummyDataUtility dummyDataUtility = new DummyDataUtility();
        dummyDataUtility.buildGuideList(getApplicationContext());
        List<Garden> allGardens = new ArrayList<>();
        final List<Garden> gardens = new ArrayList<>();
        final List<Garden> savedGardens = new ArrayList<>();
        allGardens.addAll(dummyDataUtility.gardenList);
        Log.d("Dummy garden List", "size :" +allGardens.size());
        GardensDataManager.populateDBWithList(allGardens, GardensDatabase.getGardensDatabase(getApplicationContext()));

        GardensDataManager.getDummySavedGardens(GardensDatabase.getGardensDatabase(getApplicationContext()), new DummyDataListener() {
            @Override
            public void postExecute(List<Garden> gardenList) {
                savedGardens.addAll(gardenList);
                Log.d("Dummy saved garden List", " database size :" + savedGardens.size());
            }
        });
        GardensDataManager.getAllDummyGardens(GardensDatabase.getGardensDatabase(getApplicationContext()), new DummyDataListener() {
            @Override
            public void postExecute(List<Garden> gardenList) {
                gardens.addAll(gardenList);
                Log.d("Dummy garden List", " database size :" + gardens.size());
            }
        });

    }
}
