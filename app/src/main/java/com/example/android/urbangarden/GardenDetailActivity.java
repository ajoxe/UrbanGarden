package com.example.android.urbangarden;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.urbangarden.location.MapsActivity;

public class GardenDetailActivity extends AppCompatActivity {

    private TextView parkName, parkAddress;
    private ImageView parkImage;
    private Button favesButton;
    Context context;

    private String name, address;
    Button getGardenLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_detail);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.roundedshapegreen));

        Intent intent = getIntent();
//        Bundle b = intent.getExtras();
        //        String getName =intent.getStringExtra("name");
        //      Log.d("PARKNAME", getName);
//            parkName.setText(getName);
        String getName = intent.getStringExtra("name");

        parkName = findViewById(R.id.gardenName);
        parkAddress = findViewById(R.id.gardenAddress);
        parkImage = findViewById(R.id.gardenImage);
        favesButton = findViewById(R.id.favoriteButtonImg);
        getSupportActionBar().setTitle(getName);

        getGardenLocation = (Button) findViewById(R.id.mapButton);

        getGardenLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GardenDetailActivity.this, MapsActivity.class);
                intent.putExtra("GardenName",parkName.getText().toString());
                intent.putExtra("GardenAddress",parkAddress.getText().toString() + "New York, NY");
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
//        Bundle b = intent.getExtras();
        //        String getName =intent.getStringExtra("name");
        //      Log.d("PARKNAME", getName);
//            parkName.setText(getName);
        final String getName = intent.getStringExtra("name");

        parkName.setText(getName);

        String getAddress = intent.getStringExtra("address");
        parkAddress.setText(getAddress);

        favesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                favesButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_filter_vintage_black2_24dp));
                Toast.makeText(getApplicationContext(), getName + " added to favorites!", Toast.LENGTH_LONG).show();
            }
        });

    }



}

