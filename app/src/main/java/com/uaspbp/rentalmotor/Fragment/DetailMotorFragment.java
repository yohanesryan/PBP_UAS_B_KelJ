package com.uaspbp.rentalmotor.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.uaspbp.rentalmotor.Api.ApiClient;
import com.uaspbp.rentalmotor.Api.ApiInterface;
import com.uaspbp.rentalmotor.Edit.EditMotor;
import com.uaspbp.rentalmotor.R;
import com.uaspbp.rentalmotor.Response.MotorResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailMotorFragment extends DialogFragment {
    private TextView twNama, twHarga;
    private String sIdMotor, sNama, sHarga;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    private Button btnDelete, btnEdit;

    public static DetailMotorFragment newInstance() {return new DetailMotorFragment();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.detail_motor_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = view.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twNama = view.findViewById(R.id.twNamaMotor);
        twHarga = view.findViewById(R.id.twHarga);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);

        sIdMotor = getArguments().getString("id", "");
        loadMotorById(sIdMotor);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(sIdMotor);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditMotor.class);
                i.putExtra("motorId", sIdMotor);
                startActivity(i);
                dismiss();
            }
        });

        return view;
    }

    private void loadMotorById(String id){
        ApiInterface apiServiceMotorId = ApiClient.getClient().create(ApiInterface.class);
        Call<MotorResponseObject> getMotor = apiServiceMotorId.getMotorById(id, "data");

        getMotor.enqueue(new Callback<MotorResponseObject>() {
            @Override
            public void onResponse(Call<MotorResponseObject> call, Response<MotorResponseObject> response) {
                sNama = response.body().getMotor().getNama_motor();
                sHarga = response.body().getMotor().getHarga();

                twNama.setText(sNama);
                twHarga.setText(sHarga);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MotorResponseObject> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void delete(String id){
        ApiInterface apiDeleteMotor = ApiClient.getClient().create(ApiInterface.class);
        Call<MotorResponseObject> reqDeleteMotor = apiDeleteMotor.deleteMotor(id);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        reqDeleteMotor.enqueue(new Callback<MotorResponseObject>() {
                            @Override
                            public void onResponse(Call<MotorResponseObject> call, Response<MotorResponseObject> response) {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//                                Log.i("DELETE Motor", response.body().getMessage());
//                                Intent intent = new Intent(getActivity(), DaftarMotorAdmin.class);
//                                startActivity(intent);
                                dismiss();
                            }

                            @Override
                            public void onFailure(Call<MotorResponseObject> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                Log.i("DELETE MOTOR", "Msg: " +t.getMessage());
                            }
                        });
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure want to delete this ?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}