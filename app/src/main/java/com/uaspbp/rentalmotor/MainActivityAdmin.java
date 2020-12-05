package com.uaspbp.rentalmotor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.uaspbp.rentalmotor.Api.ApiClient;
import com.uaspbp.rentalmotor.Api.ApiInterface;
import com.uaspbp.rentalmotor.Daftar.DaftarMotorAdmin;
import com.uaspbp.rentalmotor.Daftar.DaftarSewaUser;
import com.uaspbp.rentalmotor.Profile.ProfileUser;
import com.uaspbp.rentalmotor.Response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityAdmin extends AppCompatActivity {
    private String email, id, test;

    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    public static final int mode = Activity.MODE_PRIVATE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getIntent().getStringExtra("email");
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Login", mode);

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        Button informasi = findViewById(R.id.btn_info_motor);
        Button sewa = findViewById(R.id.btn_sewa);
        Button map = findViewById(R.id.btn_map);
        Button profile = findViewById(R.id.btn_profile);
        Button credit = findViewById(R.id.btn_about);
        Button signout = findViewById(R.id.btn_signout);

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowCredit.class);
                startActivity(intent);
            }
        });

//        btnSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), SignOutUser.class);
//                startActivity(intent);
//            }
//        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileUser.class);
                startActivity(intent);
            }
        });

        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DaftarMotorAdmin.class);
                startActivity(intent);
            }
        });

        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DaftarSewaUser.class);
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LokasiActivity.class);
                startActivity(intent);
            }
        });

        //load data dlu, lalu save id sm email ke shared preferences
        loadUserByEmail(email);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadUserByEmail(String email) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserByEmail(email, "data");

        add.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(MainActivityAdmin.this, "Login Success", Toast.LENGTH_SHORT).show();
                Log.i("register", "msg: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
                id = response.body().getUser().getId();
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email", email);
                editor.putString("id", id);
                editor.apply();

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivityAdmin.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}
