package com.example.rina.new_app_help_me.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.helper.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    private Button Login;
    private Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Login=(Button)findViewById(R.id.button2);
        SignUp=(Button)findViewById(R.id.button);

        //if user is already logged in openeing the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Home_dashboard_activity.class));
        }


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
}
