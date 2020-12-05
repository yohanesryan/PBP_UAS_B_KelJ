package com.uaspbp.rentalmotor.Edit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.gson.GsonBuilder;
import com.uaspbp.rentalmotor.Api.ApiClient;
import com.uaspbp.rentalmotor.Api.ApiInterface;
import com.uaspbp.rentalmotor.R;
import com.uaspbp.rentalmotor.Response.UserResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUser extends AppCompatActivity {

    private EditText editNama, editEmail;
    private String sIdUser, sNama, sEmail;
    private MaterialButton btnEdit, btnCancel;
    private String email,id;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    public static final int mode = Activity.MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        sharedPreferences = getSharedPreferences("Login", mode);
        email = sharedPreferences.getString("email", "");
        id = sharedPreferences.getString("id", "");

        btnEdit = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        editNama = findViewById(R.id.etNama);
        editEmail = findViewById(R.id.etEmail);

        progressDialog = new ProgressDialog(this);
        progressDialog.show();

//        id = getIntent().getStringExtra("id");
//        Log.i("EDIT USER ID", "msg: " +id);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNama.getText().toString().isEmpty()){
                    editNama.setError("Nama Harus Diisi");
                    editNama.requestFocus();
                }else{
                    progressDialog.show();
                    update();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadUserByEmail(email);
    }

    private void loadUserByEmail(String email) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserByEmail(email, "data");

        add.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.i("EDIT", "msg: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
                sNama = response.body().getUser().getNama();
                sEmail = response.body().getUser().getEmail();
                sIdUser = response.body().getUser().getId();

                editNama.setText(sNama);
                editEmail.setText(sEmail);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(EditUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }

    private void update() {
        ApiInterface apiServiceUpdateUser = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> reqUpdateUser = apiServiceUpdateUser.updateUser(id, editNama.getText().toString(),
                                                                            editEmail.getText().toString());

        reqUpdateUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(EditUser.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                Log.i("EDIT", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                progressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(EditUser.this, "Kesalahan Jaringan", Toast.LENGTH_LONG).show();
                Log.i("EDIT", t.getMessage());
                progressDialog.dismiss();
            }
        });

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
