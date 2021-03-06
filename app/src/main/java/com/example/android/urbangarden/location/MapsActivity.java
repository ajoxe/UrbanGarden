package com.example.android.urbangarden.location;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.android.urbangarden.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private String name;
    private String address;
    private String[] addressArray;
    private String[] namesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Log.d("MapActivity", "onCreate: This runs");

        Intent intent = getIntent();
        name = intent.getStringExtra("GardenName");
        address = intent.getStringExtra("GardenAddress");
        try{
            addressArray = intent.getStringArrayExtra("GardenAddressList");
            Log.d("address array", "size" + addressArray.length);
            namesArray = intent.getStringArrayExtra("GardenNameList");
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

        Log.d("MapActivity", "onMapReady: This loads");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener <Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object

                                double lat = location.getLatitude();
                                double lng = location.getLongitude();
                                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Current Location"));
                            }
                        }
                    });
        }


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
        } else {
            mMap.setMyLocationEnabled(true);
        }

//        it works, but we need to get a complete address to get an correct location, the current address we get only has street number and name
        Geocoder coder = new Geocoder(getApplicationContext());
        List <Address> address1;
        LatLng p1 = null;
        if (address != null) {
            try {
                // May throw an IOException
                address1 = coder.getFromLocationName(address, 5);
                if (address1 != null) {
                    Address location = address1.get(0);
                    p1 = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(p1).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

                    //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(p1, 0,0,0)));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p1, 13));

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                            .zoom(17)                   // Sets the zoom
                            .bearing(90)                // Sets the orientation of the camera to east
                            .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



        if (addressArray != null) {
            List <Address> addressList = new ArrayList<>();
            List <Address> addressCoder;
            String[] smallAddressList = new String[10];
            LatLng p2;
            if(addressArray.length > 10){
                for (int i = 0; i< 10; i++){
                    smallAddressList[i] = addressArray[i];
                }
            } else if(addressArray.length > 5){
                for (int i = 0; i< 5; i++){
                    smallAddressList[i] = addressArray[i];
                }
            } else if (addressArray.length >3){
                for (int i = 0; i< 3; i++){
                    smallAddressList[i] = addressArray[i];
                }
            } else {
                smallAddressList[0] = addressArray[0];
            }

            try {
                // May throw an IOException
                for (String addressString : smallAddressList){
                    addressCoder = coder.getFromLocationName(addressString, 3);
                    Log.d("addressString", "string" + addressString);
                    Log.d("addressCoder", "size " + addressCoder.size());
                    addressList.add(addressCoder.get(0));
                }
                if (addressList != null) {
                    for (int i=0; i<addressList.size(); i++){
                        Address locationL = addressList.get(i);
                        p2 = new LatLng(locationL.getLatitude(), locationL.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(p2).title(namesArray[i]).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));
                    }
                    Address locationL = addressList.get(0);
                    p2 = new LatLng(locationL.getLatitude(), locationL.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p2, 13));


                    /*mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p1, 13));

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                            .zoom(17)                   // Sets the zoom
                            .bearing(90)                // Sets the orientation of the camera to east
                            .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

//        1. auto zoom and auto center
        /*LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }*/
    }

}


//2. get all gardens on the map