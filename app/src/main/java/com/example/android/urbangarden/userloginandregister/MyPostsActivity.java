package com.example.android.urbangarden.userloginandregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.urbangarden.R;

public class MyPostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.roundedshapegreen));
        getSupportActionBar().setTitle("My Garden");
    }
}
