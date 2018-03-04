package com.example.android.urbangarden;

import android.content.Intent;

import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;


import com.example.android.urbangarden.controller.GardenAdapter;
import com.example.android.urbangarden.location.GPSTracker;

import com.example.android.urbangarden.Networking.NetworkUtility;
import com.example.android.urbangarden.Networking.RetrofitListener;
import com.example.android.urbangarden.model.Garden;
import com.example.android.urbangarden.userloginandregister.LoginActivity;
import com.example.android.urbangarden.userloginandregister.MyPostsActivity;
import com.example.android.urbangarden.userloginandregister.SettingsActivity;
import com.example.android.urbangarden.userloginandregister.UserActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GardenSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    EditText searchEditText;
    String spinnerOption;
    String searchQuery;
    String queryType;

    CheckBox favesCheckBox;


    String zipEditTextString;

    GPSTracker gps;
    double latitude;
    double longitude;

    SharedPreferences registerPref;
    String user;

    private RecyclerView recyclerView;
    List<Garden> gardenList = new ArrayList<>();

    private final String TAG = getClass().getSimpleName();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_search);
//        changeActionBarColor();
        setSearchSpinner();

        getLocation();
        recyclerView = findViewById(R.id.search_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        searchEditText = (EditText) findViewById(R.id.search_query_edit_text);

        Intent intent = getIntent();
        user = intent.getStringExtra("currentUser");

    }


    //sets up the spinner
    public void setSearchSpinner() {
        spinner = (Spinner) findViewById(R.id.boro_choices_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.boro_spinner_options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void onSearchClick(View view){
        zipEditTextString = searchEditText.getText().toString();
        if (!zipEditTextString.equals("")){
            queryType = "postcode";
            searchQuery = zipEditTextString;
        }
        Log.d("search query", searchQuery);

        makeNetworkCall(searchQuery, queryType, new RetrofitListener() {
            @Override
            public void updateUI(Garden[] gardens) {
                Log.d("update UI", String.valueOf(gardens.length));
                //TODO add data to Recycler view
                gardenList.addAll(Arrays.asList(gardens));
                recyclerView.setAdapter(new GardenAdapter(gardenList));
            }

            @Override
            public void onFailureAlert() {

                alertUserAboutError();

            }
        });

    }

    public void makeNetworkCall(String searchQuery, String queryType, RetrofitListener listener) {
        NetworkUtility utility = NetworkUtility.getUtility();
        utility.getGardensByQuery(searchQuery, queryType, listener);
    }


    private void alertUserAboutError() {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.show(getFragmentManager(), "error_dialog");

    }

    //spinner selection on click
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerOption = (String) parent.getItemAtPosition(position);
        if (!spinnerOption.equals("Search by borough")){
            queryType = "boro";
            switch(spinnerOption) {
                case "Brooklyn":
                    searchQuery = "B";
                    break;
                case "Manhattan":
                    searchQuery = "M";
                    break;
                case "Bronx":
                    searchQuery = "X";
                    break;
                case "Queens":
                    searchQuery = "Q";
                    break;
                case "Staten Island":
                    searchQuery = "R";
                    break;
            }

            }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.garden_options_menu, menu);

        if(user.equals(null)){
            hideOption(1);
        }else {
            hideOption(0);
        }
        return true;
    }

    private void hideOption(int id)
    {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id)
    {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                    Intent intent = new Intent(GardenSearchActivity.this, LoginActivity.class);
//                intent.putExtra("myGardenList", "");
                    startActivity(intent);
                Log.e(TAG, "login button was clicked");
                break;

            case R.id.user_profile:
                Intent intent0 = new Intent(GardenSearchActivity.this, UserActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent0);
                Log.e(TAG, "my posts button was clicked");
                break;

            case R.id.my_posts:
                Intent intent1 = new Intent(GardenSearchActivity.this, MyPostsActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent1);
                Log.e(TAG, "my posts button was clicked");
                break;

            case R.id.fav_list:
                Intent intent2 = new Intent(GardenSearchActivity.this, MyGardensActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent2);
                Log.e(TAG, "Fav list button was clicked");
                break;

            case R.id.setting:
                Intent intent3 = new Intent(GardenSearchActivity.this, SettingsActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent3);
                Log.e(TAG, "Setting was clicked");
                break;

            case R.id.sign_out:
                Intent intent4 = new Intent(GardenSearchActivity.this, LoginActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent4);
                Log.e(TAG, "Fav button was clicked");
                break;

            default:
                Log.e(TAG, "No button was clicked");
        }
        return true;
    }


    private void getLocation() {
        gps = new GPSTracker(GardenSearchActivity.this);

        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Log.d("latitude", String.valueOf(latitude));
            Log.d("longtitude", String.valueOf(longitude));
        } else {
            gps.showSettingsAlert();
        }
    }
    
//    public void changeActionBarColor(){
//        android.app.ActionBar actionBar = getActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
//    }
}
