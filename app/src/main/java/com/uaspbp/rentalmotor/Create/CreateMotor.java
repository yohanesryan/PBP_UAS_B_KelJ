package com.uaspbp.rentalmotor.Create;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.GsonBuilder;
import com.uaspbp.rentalmotor.Api.ApiClient;
import com.uaspbp.rentalmotor.Api.ApiInterface;
import com.uaspbp.rentalmotor.MainActivity;
import com.uaspbp.rentalmotor.R;
import com.uaspbp.rentalmotor.Response.MotorResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMotor extends AppCompatActivity {

    TextInputEditText tvNamaMotor, tvHarga;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_motor);

        tvNamaMotor = (TextInputEditText) findViewById(R.id.tvNamaMotor);
        tvHarga = (TextInputEditText) findViewById(R.id.tvHarga);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvNamaMotor.getText().toString().isEmpty()) {
                    tvNamaMotor.setError("Nama MotorHarus Diisi");
                    tvNamaMotor.requestFocus();
                }
                else if(tvHarga.getText().toString().isEmpty()) {
                    tvHarga.setError("Harga Motor Harus Diisi");
                    tvHarga.requestFocus();
                }
                else{
                    createMotor();
                }

            }
        });
    }

    private void createMotor() {
        ApiInterface apiServiceCreate = ApiClient.getClient().create(ApiInterface.class);
        Call<MotorResponseObject> addMotor = apiServiceCreate.createMotor(tvNamaMotor.getText().toString(),
                tvHarga.getText().toString());
        addMotor.enqueue(new Callback<MotorResponseObject>() {
            @Override
            public void onResponse(Call<MotorResponseObject> call, Response<MotorResponseObject> response) {
                Toast.makeText(CreateMotor.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                onBackPressed();

                Log.i("create", "msg: "+ new GsonBuilder().setPrettyPrinting().create().toJson(response));
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MotorResponseObject> call, Throwable t) {
                Toast.makeText(CreateMotor.this, t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("response", "msg: " +t.getMessage());

            }
        });

    }
}
