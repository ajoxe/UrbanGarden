package com.example.android.urbangarden.userloginandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.urbangarden.MyGardensActivity;
import com.example.android.urbangarden.R;
import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;

public class UserActivity extends AppCompatActivity {

    public static final String TAG = UserActivity.class.getSimpleName();

    private Menu menu;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.roundedshapegreen));
        getSupportActionBar().setTitle("My Profile");
    }

}
