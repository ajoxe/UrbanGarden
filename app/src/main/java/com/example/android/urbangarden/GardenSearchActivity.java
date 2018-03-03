package com.example.android.urbangarden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class GardenSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_search);
        setSearchSpinner();
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
}
