package com.example.android.urbangarden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.urbangarden.location.GPSTracker;


public class GardenSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    EditText searchEditText;

    GPSTracker gps;
    double latitude;
    double longitude;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_search);
        setSearchSpinner();
        getLocation();

    }
    //sets up the spinner
    public void setSearchSpinner(){
        spinner = (Spinner) findViewById(R.id.search_choices_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.search_spinner_options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    //spinner selection on click
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String searchOption = (String) parent.getItemAtPosition(position);
        //TODO do stuff
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.garden_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fav:
                Intent intent = new Intent(GardenSearchActivity.this, MyGardensActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent);
                Log.e(TAG,"Fav button was clicked");
                break;
            default:
                Log.e(TAG,"No button was clicked");
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
}
