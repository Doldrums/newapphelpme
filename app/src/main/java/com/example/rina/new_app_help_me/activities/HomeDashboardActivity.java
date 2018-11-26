package com.example.rina.new_app_help_me.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.helper.SharedPrefManager;
import com.example.rina.new_app_help_me.models.User;

public class HomeDashboardActivity extends AppCompatActivity {

    private SharedPrefManager prefManager;

    private CardView exit;
    private CardView settings;
    private CardView autoEnter, calendar, support, birga;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard_activity);

        prefManager = SharedPrefManager.getInstance(this);

        exit = findViewById(R.id.exit);
        settings = findViewById(R.id.settings);
        autoEnter = findViewById(R.id.CheckIn);
        calendar = findViewById(R.id.calendar);
        support = findViewById(R.id.support);
        birga = findViewById(R.id.birga);

        final User user = prefManager.getUser();

        //TODO все листенеры — в отдельные поля
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.logout();
                startActivity(new Intent(HomeDashboardActivity.this, MainActivity.class));

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeDashboardActivity.this, SettingsActivity.class));
            }
        });

        autoEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user.getType().equals("TeacherActivity")) {
                    startActivity(new Intent(HomeDashboardActivity.this, TeacherActivity.class));

                } else {
                    startActivity(new Intent(HomeDashboardActivity.this, StudentActivity.class));
                }

            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeDashboardActivity.this, CalendarActivity.class));
            }
        });


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeDashboardActivity.this, SupportActivity.class));
            }
        });

        birga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeDashboardActivity.this, BirgaActivity.class));
            }
        });
    }


    @Override
    public void onBackPressed() {

    }
}
