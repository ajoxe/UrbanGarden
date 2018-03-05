package com.example.android.urbangarden.userloginandregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.android.urbangarden.GardenSearchActivity;
import com.example.android.urbangarden.MyGardensActivity;
import com.example.android.urbangarden.R;
import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private EditText username;
    private EditText password;
    private CheckBox checkBox;
    private Button submitButton;
    private Button registerButton;
    private SharedPreferences login;
    private Menu menu;
    String user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.roundedshapegreen));
        getSupportActionBar().setTitle("Login");

        username = (EditText) findViewById(R.id.username_edittext);
        password = (EditText) findViewById(R.id.password_edittext);
        checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        submitButton = (Button) findViewById(R.id.submit_button);
        registerButton = (Button) findViewById(R.id.register_button);

        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        if (login.getBoolean("isChecked", false)) {
            username.setText(login.getString("username", null));
            password.setText(login.getString("password", null));
            checkBox.setChecked(login.getBoolean("isChecked", false));
        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = login.edit();
                if (checkBox.isChecked()) {
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.commit();
                } else {
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.commit();
                }

                Intent intent = new Intent(LoginActivity.this, GardenSearchActivity.class);
                intent.putExtra("currentUser", username.getText().toString());
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("testKey", SHARED_PREFS_KEY);
                startActivity(intent);
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
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
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
                Intent intent2 = new Intent(LoginActivity.this, MyGardensActivity.class);
//                intent.putExtra("myGardenList", "");
                startActivity(intent2);
                Log.e(TAG, "Fav list button was clicked");
                break;

            case R.id.setting:
                Intent intent3 = new Intent(LoginActivity.this, SettingsActivity.class);
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
                Intent searchIntent = new Intent(LoginActivity.this, GardenSearchActivity.class);
                startActivity(searchIntent);
                break;

            default:
                Log.e(TAG, "No button was clicked");
        }
        return true;
    }
}


