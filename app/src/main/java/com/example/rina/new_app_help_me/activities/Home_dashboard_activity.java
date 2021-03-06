package com.example.rina.new_app_help_me.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;


import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.helper.SharedPrefManager;
import com.example.rina.new_app_help_me.models.User;

public class Home_dashboard_activity extends AppCompatActivity {
    private CardView Exit;
    private CardView Settings;
    private CardView AutoEnter, Calendar, Support, Birga;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard_activity);
        Exit = (CardView)findViewById(R.id.exit);
        Settings=(CardView)findViewById(R.id.settings);
        AutoEnter =(CardView) findViewById(R.id.CheckIn);
        Calendar =(CardView) findViewById(R.id.calendar);
        Support =(CardView) findViewById(R.id.support);
        Birga =(CardView) findViewById(R.id.birga);
        final User user = SharedPrefManager.getInstance(this).getUser();



        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getBaseContext()).logout();
                startActivity(new Intent(Home_dashboard_activity.this, MainActivity.class));

            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_dashboard_activity.this, SettingsActivity.class));
            }
        });
        AutoEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user.getType().equals("Teacher")) {
                    startActivity(new Intent(Home_dashboard_activity.this, Teacher.class));

                }

                else {
                    startActivity(new Intent(Home_dashboard_activity.this, Student.class));
                }

            }
        });

        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_dashboard_activity.this, CalendarActivity.class));
            }
        });


        Support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_dashboard_activity.this, SupportActivity.class));
            }
        });

        Birga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_dashboard_activity.this, BirgaActivity.class));
            }
        });
    }




    @Override
    public void onBackPressed() {

    }
}
