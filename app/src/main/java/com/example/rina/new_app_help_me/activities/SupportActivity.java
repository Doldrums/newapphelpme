package com.example.rina.new_app_help_me.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rina.new_app_help_me.R;

public class SupportActivity extends AppCompatActivity {

    private Button Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Exit = (Button)findViewById(R.id.buttonExit);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SupportActivity.this, Home_dashboard_activity.class));
            }
        });
    }
}
