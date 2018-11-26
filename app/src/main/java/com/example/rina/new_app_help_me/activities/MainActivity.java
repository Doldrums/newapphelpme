package com.example.rina.new_app_help_me.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.helper.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    private Button Login;
    private Button SignUp;

    private SharedPrefManager prefManager;
    private View.OnClickListener loginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent toSignInIntent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(toSignInIntent);
        }
    };
    private View.OnClickListener signUpButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toSignUpIntent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(toSignUpIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Login = findViewById(R.id.button2);
        SignUp = findViewById(R.id.button);

        prefManager = SharedPrefManager.getInstance(this);

        //if user is already logged in opening the profile activity
        checkLoginStatus();

        Login.setOnClickListener(loginButtonListener);

        SignUp.setOnClickListener(signUpButtonListener);
    }

    @Override
    public void onBackPressed() {

    }

    private void checkLoginStatus() {
        if (prefManager.isLoggedIn()) {
            finish();
            startActivity(new Intent(this, HomeDashboardActivity.class));
        }
    }
}
