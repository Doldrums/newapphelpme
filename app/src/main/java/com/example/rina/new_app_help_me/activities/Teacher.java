package com.example.rina.new_app_help_me.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;


import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.api.APIService;
import com.example.rina.new_app_help_me.api.APIUrl;
import com.example.rina.new_app_help_me.models.User;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Teacher extends AppCompatActivity {
    private IntentIntegrator qrScan;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();
        text =(TextView) findViewById(R.id.textW);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                final String email = "1"; //имейл ученика по которому искать в базе


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIUrl.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final APIService service = retrofit.create(APIService.class);

                Call<User> call = service.getUser(email);

                final ProgressDialog progressDialog = new ProgressDialog(this);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        text.setText(String.valueOf(response.raw().body()));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
