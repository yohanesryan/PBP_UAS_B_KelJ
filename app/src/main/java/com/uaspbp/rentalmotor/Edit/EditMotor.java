package com.uaspbp.rentalmotor.Edit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.uaspbp.rentalmotor.Api.ApiClient;
import com.uaspbp.rentalmotor.Api.ApiInterface;
import com.uaspbp.rentalmotor.R;
import com.uaspbp.rentalmotor.Response.MotorResponseObject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMotor extends AppCompatActivity {
    TextInputEditText tvNamaMotor, tvHarga;
    Button btnAdd;

    //private TextView twNama, twHarga;
    private String sIdMotor, sNama, sHarga;
    //private ImageButton ibClose;
    private ProgressDialog progressDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_motor);

        id = getIntent().getStringExtra("motorId");

        tvNamaMotor = (TextInputEditText) findViewById(R.id.tvNamaMotor);
        tvHarga = (TextInputEditText) findViewById(R.id.tvHarga);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvNamaMotor.getText().toString().isEmpty()) {
                    tvNamaMotor.setError("Nama Motor Harus Diisi");
                    tvNamaMotor.requestFocus();
                }
                else if(tvHarga.getText().toString().isEmpty()) {
                    tvHarga.setError("Harga Motor Harus Diisi");
                    tvHarga.requestFocus();
                }
                else{
                    updateMotor();
                }

            }
        });

        loadMotorById(id);
    }

    private void updateMotor() {
        ApiInterface apiServiceCreate = ApiClient.getClient().create(ApiInterface.class);
        Call<MotorResponseObject> addMotor = apiServiceCreate.updateMotor(id, tvNamaMotor.getText().toString(),
                tvHarga.getText().toString());
        addMotor.enqueue(new Callback<MotorResponseObject>() {
            @Override
            public void onResponse(Call<MotorResponseObject> call, Response<MotorResponseObject> response) {
                Toast.makeText(EditMotor.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                onBackPressed();

//                Log.i("create", "msg: "+ new GsonBuilder().setPrettyPrinting().create().toJson(response));
//                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MotorResponseObject> call, Throwable t) {
                Toast.makeText(EditMotor.this, t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("response", "msg: " +t.getMessage());

            }
        });

    }

    private void loadMotorById(String id){
        ApiInterface apiServiceMotorId = ApiClient.getClient().create(ApiInterface.class);
        Call<MotorResponseObject> getMotor = apiServiceMotorId.getMotorById(id, "data");

        getMotor.enqueue(new Callback<MotorResponseObject>() {
            @Override
            public void onResponse(Call<MotorResponseObject> call, Response<MotorResponseObject> response) {
                sNama = response.body().getMotor().getNama_motor();
                sHarga = response.body().getMotor().getHarga();

                tvNamaMotor.setText(sNama);
                tvHarga.setText(sHarga);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MotorResponseObject> call, Throwable t) {
                Toast.makeText(EditMotor.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}
