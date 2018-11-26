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
import com.example.rina.new_app_help_me.models.MessageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BirgaActivity extends AppCompatActivity {

    private APIService apiService;

    private RecyclerView recyclerViewMessages;
    private RecyclerView.Adapter adapter;
    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LayoutInflater li = LayoutInflater.from(BirgaActivity.this);
            View promptsView = li.inflate(R.layout.dialog_send_birga, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BirgaActivity.this);
            alertDialogBuilder.setView(promptsView);

            final EditText editTextTitle = promptsView.findViewById(R.id.editTextTitle);
            final EditText editTextMessage = promptsView.findViewById(R.id.editTextMessage);

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Отправить",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    //getting the values
                                    String title = editTextTitle.getText().toString().trim();
                                    String message = editTextMessage.getText().toString().trim();

                                    //sending the message
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birga);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(fabClickListener);
    }

    private void sendMessage(String title, String message) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Отправка сообщения...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /*TODO Такие вещи обычно выносят в отедльный класс.
         * Сейчас ты кучу раз создаёшь одно и то же. Оно занимает место в памяти,
         * может нехорошо работать друг с другом и всё такое.
         * Лучше вынести в синглтон-класс.
         * Когда узнаешь, что такое Dagger или Koin, в таких случаях будешь пользоваться ими.
         * */
        APIService service = retrofit.create(APIService.class);

        Call<MessageResponse> call = service.sendMessage(
                SharedPrefManager.getInstance(this).getUser().getId(),
                title,
                message
        );

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(BirgaActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BirgaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
