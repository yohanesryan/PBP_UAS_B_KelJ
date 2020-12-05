package com.uaspbp.rentalmotor.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.uaspbp.rentalmotor.Api.ApiClient;
import com.uaspbp.rentalmotor.Api.ApiInterface;
import com.uaspbp.rentalmotor.R;
import com.uaspbp.rentalmotor.Response.MotorResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailMotorFragmentUser extends DialogFragment {

    private TextView twNama, twHarga;
    private String sIdMotor, sNama, sHarga;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    public static DetailMotorFragmentUser newInstance() {return new DetailMotorFragmentUser();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.detail_motor_fragment_user, container, false);

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

        sIdMotor = getArguments().getString("id", "");
        loadMotorById(sIdMotor);

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

}