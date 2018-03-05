package com.example.android.urbangarden.userloginandregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.urbangarden.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.roundedshapegreen));
        getSupportActionBar().setTitle("My Profile");
    }
}
