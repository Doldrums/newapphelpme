package com.example.rina.new_app_help_me.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.api.APIService;
import com.example.rina.new_app_help_me.api.APIUrl;
import com.example.rina.new_app_help_me.helper.SharedPrefManager;
import com.example.rina.new_app_help_me.models.BirgaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BirgaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birga);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(BirgaActivity.this);
                View promptsView = li.inflate(R.layout.dialog_send_birga, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BirgaActivity.this);
                alertDialogBuilder.setView(promptsView);

                final EditText editTextTitle = (EditText) promptsView.findViewById(R.id.editTextTitle);
                final EditText editTextMessage = (EditText) promptsView.findViewById(R.id.editTextMessage);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Отправить",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        //получаем значения
                                        String title = editTextTitle.getText().toString().trim();
                                        String message = editTextMessage.getText().toString().trim();

                                        //отправляем
                                        sendMessage(title, message);
                                    }
                                })
                        .setNegativeButton("Закрыть",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });
    }


    private void sendMessage(String title, String message) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Отправка сообщения...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<BirgaResponse> call = service.sendMessage(
                SharedPrefManager.getInstance(this).getUser().getId(),
                title,
                message
        );

        call.enqueue(new Callback<BirgaResponse>() {
            @Override
            public void onResponse(Call<BirgaResponse> call, Response<BirgaResponse> response) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(BirgaActivity.this, response.body().getBirga(), Toast.LENGTH_LONG).show();
                } catch (NullPointerException e) {
                    Toast.makeText(BirgaActivity.this, "Что-то пошло не так, попробуйте еще раз", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<BirgaResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BirgaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

        
}
