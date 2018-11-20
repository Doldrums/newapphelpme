package com.example.rina.new_app_help_me.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.api.APIService;
import com.example.rina.new_app_help_me.api.APIUrl;
import com.example.rina.new_app_help_me.helper.SharedPrefManager;
import com.example.rina.new_app_help_me.models.Result;
import com.example.rina.new_app_help_me.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonUpdate;
    private TextView buttonReturn;
    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonReturn =(TextView) findViewById(R.id.Return);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        radioGender = (RadioGroup) findViewById(R.id.radioGender);


        buttonUpdate.setOnClickListener(this);

        User user = SharedPrefManager.getInstance(this).getUser();

        editTextName.setText(user.getName());
        editTextEmail.setText(user.getEmail());
        editTextPassword.setText("0000");

        if (user.getGender().equalsIgnoreCase("male")) {
            radioGender.check(R.id.radioMale);
        } else {
            radioGender.check(R.id.radioFemale);
        }



        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, Home_dashboard_activity.class));
            }
        });

    }
        private void updateUser() {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Updating...");
            progressDialog.show();

            final RadioButton radioSex = (RadioButton) findViewById(radioGender.getCheckedRadioButtonId());

            String name = editTextName.getText().toString().trim();
            final String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String gender = radioSex.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrl.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIService service = retrofit.create(APIService.class);

            User user = new User(SharedPrefManager.getInstance(this).getUser().getId(), name, email, password, gender);

            Call<Result> call = service.updateUser(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getGender()
            );

            call.enqueue(new Callback<Result>() {
                @Override

                public void onResponse(Call<Result> call, Response<Result> response) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    if (!response.body().getError()) {
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                        startActivity(new Intent(SettingsActivity.this, Home_dashboard_activity.class));
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }





    @Override
    public void onClick(View view) {
        if (view == buttonUpdate) {
            updateUser();
        }
    }
}
