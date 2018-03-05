package com.example.android.urbangarden;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.urbangarden.R;
import com.example.android.urbangarden.controller.GardenAdapter;
import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;
import com.example.android.urbangarden.model.Garden;
import com.example.android.urbangarden.userloginandregister.LoginActivity;
import com.example.android.urbangarden.userloginandregister.MyPostsActivity;
import com.example.android.urbangarden.userloginandregister.SettingsActivity;
import com.example.android.urbangarden.userloginandregister.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class MyGardensActivity extends AppCompatActivity {

    public static final String TAG = MyGardensActivity.class.getSimpleName();

    List<Garden> savedGardens = GardensDataManager.savedGardenList;
    private RecyclerView recyclerView;
    GardenAdapter gardenAdapter;
    Context context;
    String user = null;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gardens);

       savedGardens.addAll(GardensDataManager.getSavedGardens(GardensDatabase.getGardensDatabase(getApplicationContext())));
        Log.d("saved activity", "list size" + savedGardens.size());
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.roundedshapegreen));
        getSupportActionBar().setTitle("My Garden");

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.saved_recycler_view);

        gardenAdapter = new GardenAdapter(savedGardens, getApplicationContext());
        recyclerView.setAdapter(gardenAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                Intent intent = new Intent(MyGardensActivity.this, LoginActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent);
                Log.e(TAG, "login button was clicked");
                break;

            /*case R.id.user_profile:
                Intent intent0 = new Intent(MyGardensActivity.this, UserActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent0);
                Log.e(TAG, "my posts button was clicked");
                break;

            case R.id.my_posts:
                Intent intent1 = new Intent(MyGardensActivity.this, MyPostsActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent1);
                Log.e(TAG, "my posts button was clicked");
                break;
*/
            case R.id.fav_list:
                GardensDataManager.getSavedGardens(GardensDatabase.getGardensDatabase(getApplicationContext()));
                Intent intent2 = new Intent(MyGardensActivity.this, MyGardensActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent2);
                Log.e(TAG, "Fav list button was clicked");
                break;

            case R.id.setting:
                Intent intent3 = new Intent(MyGardensActivity.this, SettingsActivity.class);
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
                Intent searchIntent = new Intent(MyGardensActivity.this, GardenSearchActivity.class);
                startActivity(searchIntent);
                break;

            default:
                Log.e(TAG, "No button was clicked");
        }
        return true;
    }

}
