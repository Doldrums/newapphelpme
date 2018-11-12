package com.example.rina.new_app_help_me.activities;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.helper.SharedPrefManager;
import com.example.rina.new_app_help_me.models.User;

import net.glxn.qrgen.android.QRCode;


public class Student extends AppCompatActivity {
    private Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        SharedPrefManager.getInstance(getApplicationContext());
        final User user = SharedPrefManager.getInstance(this).getUser();

        exit = (Button)findViewById(R.id.buttonExit);



        Bitmap myBitmap = QRCode.from(user.getEmail()).bitmap();
        ImageView myImage = (ImageView) findViewById(R.id.pikcha);
        myImage.setImageBitmap(myBitmap);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student.this, Home_dashboard_activity.class));
            }
        });
    }
}
