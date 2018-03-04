package com.example.android.urbangarden.userloginandregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.urbangarden.R;

public class RegisterActivity extends AppCompatActivity {
    private SharedPreferences registerPrefs;
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.register_username_edittext);
        password = (EditText) findViewById(R.id.register_password_edittext);
        confirmPassword = (EditText) findViewById(R.id.confirm_password_edittext);
        submitButton = (Button) findViewById(R.id.submit_button);

        Intent intent = getIntent();
        registerPrefs = getApplicationContext().getSharedPreferences(intent.getStringExtra("testKey"), MODE_PRIVATE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = registerPrefs.edit();
                if (userName.getText() != null &&
                        password.getText() != null &&
                        confirmPassword.getText() != null &&
                        password.getText().toString().equals(
                                confirmPassword.getText().toString()
                        )) {
                    editor.putString("user" + userName.getText().toString(), userName.getText().toString());
                    editor.putString("password" + userName.getText().toString(), password.getText().toString());
                    editor.commit();
                    finish();
                }
            }
        });
    }
}
