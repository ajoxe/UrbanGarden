package com.example.android.urbangarden;

import android.content.Context;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.android.urbangarden.controller.GardenAdapter;
import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;
import com.example.android.urbangarden.location.GPSTracker;

import com.example.android.urbangarden.Networking.NetworkUtility;
import com.example.android.urbangarden.Networking.RetrofitListener;
import com.example.android.urbangarden.location.MapsActivity;
import com.example.android.urbangarden.model.Garden;
import com.example.android.urbangarden.userloginandregister.LoginActivity;
import com.example.android.urbangarden.userloginandregister.MyPostsActivity;
import com.example.android.urbangarden.userloginandregister.SettingsActivity;
import com.example.android.urbangarden.userloginandregister.UserActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GardenSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    EditText searchEditText;
    Button getUserLocationButton;
    TextView searchToggle;
    CheckBox favesCheckBox;
    LinearLayout searchLayout;
    Context context;

    String spinnerOption;
    String searchQuery;
    String queryType;
    String zipEditTextString;
    String spinnerChoiceResult = "";

    GPSTracker gps;
    double latitude;
    double longitude;

    SharedPreferences registerPref;
    String user = null;

    private RecyclerView recyclerView;
    List<Garden> gardenList = new ArrayList<>();
    GardenAdapter gardenAdapter;

    private final String TAG = getClass().getSimpleName();
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_search);
//        changeActionBarColor();
        context = getApplicationContext();
        setSearchSpinner();

        getUserLocationButton = (Button) findViewById(R.id.get_location_button);

        searchToggle = (TextView) findViewById(R.id.search_toggle_text_view);
        searchLayout = (LinearLayout) findViewById(R.id.search_layout);
        setToggleClick();
        getLocation();
        recyclerView = findViewById(R.id.search_recycler_view);
        gardenAdapter = new GardenAdapter(gardenList, context);
        recyclerView.setAdapter(gardenAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        searchEditText = (EditText) findViewById(R.id.search_query_edit_text);

        Intent intent = getIntent();
        user = intent.getStringExtra("currentUser");

        getUserLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GardenSearchActivity.this, MapsActivity.class);
                intent.putExtra("Latitude", latitude);
                intent.putExtra("Longtitude", longitude);
                startActivity(intent);
            }
        });
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


    public void onSearchClick(View view) {
        searchLayout.setVisibility(View.GONE);
        searchToggle.setVisibility(View.VISIBLE);
        zipEditTextString = searchEditText.getText().toString();
        if (!zipEditTextString.equals("")) {
            queryType = "postcode";
            searchQuery = zipEditTextString;

        } else if (!spinnerChoiceResult.equals("")){
            queryType = "boro";
            searchQuery = spinnerChoiceResult;
        } else{
            //TODO location logic for queries and parameters
        }

        Log.d(TAG, "SEARCH QUERY = " + searchQuery);
        Log.d(TAG, "QUERY TYPE = " + queryType);
        makeNetworkCall(searchQuery, queryType, new RetrofitListener() {
            @Override
            public void updateUI(Garden[] gardens) {
                Log.d("update UI", String.valueOf(gardens.length));

                if(gardenList.size() != 0){
                    gardenList.clear();
                }
                gardenList.addAll(Arrays.asList(gardens));
                gardenAdapter.notifyDataSetChanged();
                GardensDataManager.populateDBWithList(gardenList, GardensDatabase.getGardensDatabase(context));
            }

            @Override
            public void onFailureAlert() {

                alertUserAboutError();

            }
        });

    }

    public void makeCallWithZip(String zip, String type){

        makeNetworkCall(zip, type, new RetrofitListener() {
            @Override
            public void updateUI(Garden[] gardens) {
                Log.d("update UI", String.valueOf(gardens.length));
                if(gardenList.size() != 0){
                    gardenList.clear();
                }
                gardenList.addAll(Arrays.asList(gardens));
                gardenAdapter.notifyDataSetChanged();
                GardensDataManager.populateDBWithList(gardenList, GardensDatabase.getGardensDatabase(context));
            }

            @Override
            public void onFailureAlert() {
                alertUserAboutError();
            }
        });

    }

    public void makeCallWithBoro(String searchQuery, String queryType){

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
        if (!spinnerOption.equals("Search by borough")) {
            switch (spinnerOption) {
                case "Brooklyn":
                    spinnerChoiceResult = "B";
                    break;
                case "Manhattan":
                    spinnerChoiceResult = "M";
                    break;
                case "Bronx":
                    spinnerChoiceResult = "X";
                    break;
                case "Queens":
                    spinnerChoiceResult = "Q";
                    break;
                case "Staten Island":
                    spinnerChoiceResult = "R";
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
        MenuItem userMenuItem = menu.findItem(R.id.user);
        if (user != null) {
            userMenuItem.setTitle(user);
        } else {
            userMenuItem.setTitle("User");
        }
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
                GardensDataManager.getSavedGardens(GardensDatabase.getGardensDatabase(getApplicationContext()));
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
                user = null;
                updateMenuTitles();
                hideOption(user);
                showOption(user);
                Log.e(TAG, "Sign out button was clicked");
                break;

            default:
                Log.e(TAG, "No button was clicked");
        }
        return true;
    }

    public void locationClick(View view) {

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

    public void setToggleClick(){
        searchToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                spinner.setSelection(0);
                spinnerChoiceResult = "";
                zipEditTextString = "";
                searchEditText.setText("");
            }
        });

    }

//    public void changeActionBarColor(){
//        android.app.ActionBar actionBar = getActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
//    }
}
