package com.example.android.urbangarden;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;
import com.example.android.urbangarden.location.MapsActivity;
import com.example.android.urbangarden.userloginandregister.LoginActivity;
import com.example.android.urbangarden.userloginandregister.MyPostsActivity;
import com.example.android.urbangarden.userloginandregister.SettingsActivity;
import com.example.android.urbangarden.userloginandregister.UserActivity;

public class GardenDetailActivity extends AppCompatActivity {

    public static final String TAG = GardenDetailActivity.class.getSimpleName();

    private TextView parkName, parkAddress;
    private ImageView parkImage;
    private Button favesButton;
    Context context;

    private String name, address;
    Button getGardenLocation;
    private Menu menu;
    String user = null;

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
        final String getName = intent.getStringExtra("name");

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
                intent.putExtra("GardenName", parkName.getText().toString());
                intent.putExtra("GardenAddress", parkAddress.getText().toString() + "New York, NY");
                startActivity(intent);
            }
        });


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.garden_options_menu, menu);
        updateMenuTitles();
        hideOption(user);
        showOption(user);
        return true;
    }

    private void hideOption(String user) {
        MenuItem item = menu.findItem(R.id.login);
        if (user != null) {
            item.setVisible(false);
        }
    }

    private void showOption(String user) {
        MenuItem item = menu.findItem(R.id.login);
        if (user == null) {
            item.setVisible(true);
        }
    }

    private void updateMenuTitles() {
       /* MenuItem userMenuItem = menu.findItem(R.id.user);
        if (user != null) {
            userMenuItem.setTitle(user);
        } else {
            userMenuItem.setTitle("User");
        }*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent intent = new Intent(GardenDetailActivity.this, LoginActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent);
                Log.e(TAG, "login button was clicked");
                break;

            /*case R.id.user_profile:
                Intent intent0 = new Intent(GardenDetailActivity.this, UserActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent0);
                Log.e(TAG, "my posts button was clicked");
                break;*//*

            case R.id.my_posts:
                Intent intent1 = new Intent(GardenDetailActivity.this, MyPostsActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent1);
                Log.e(TAG, "my posts button was clicked");
                break;*/

            case R.id.fav_list:
                GardensDataManager.getSavedGardens(GardensDatabase.getGardensDatabase(getApplicationContext()));
                Intent intent2 = new Intent(GardenDetailActivity.this, MyGardensActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent2);
                Log.e(TAG, "Fav list button was clicked");
                break;

            case R.id.setting:
                Intent intent3 = new Intent(GardenDetailActivity.this, SettingsActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent3);
                Log.e(TAG, "Setting was clicked");
                break;

            case R.id.search_item:
                user = null;
                updateMenuTitles();
                hideOption(user);
                showOption(user);
                Log.e(TAG, "Search out button was clicked");
                Intent searchIntent = new Intent(GardenDetailActivity.this, GardenSearchActivity.class);
                startActivity(searchIntent);
                break;

            default:
                Log.e(TAG, "No button was clicked");
        }
        return true;
    }


}

