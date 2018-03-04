package com.example.android.urbangarden;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.urbangarden.R;
import com.example.android.urbangarden.controller.GardenAdapter;
import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;
import com.example.android.urbangarden.model.Garden;

import java.util.ArrayList;
import java.util.List;

public class MyGardensActivity extends AppCompatActivity {
    List<Garden> savedGardens = GardensDataManager.savedGardenList;
    private RecyclerView recyclerView;
    GardenAdapter gardenAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gardens);
        getSupportActionBar().setTitle("My Gardens");
       savedGardens.addAll(GardensDataManager.getSavedGardens(GardensDatabase.getGardensDatabase(getApplicationContext())));
        Log.d("saved activity", "list size" + savedGardens.size());

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.saved_recycler_view);

        gardenAdapter = new GardenAdapter(savedGardens, getApplicationContext());
        recyclerView.setAdapter(gardenAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
