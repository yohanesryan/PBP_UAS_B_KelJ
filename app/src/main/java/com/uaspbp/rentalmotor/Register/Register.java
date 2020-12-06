package com.uaspbp.rentalmotor.Register;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scottyab.aescrypt.AESCrypt;
import com.uaspbp.rentalmotor.Login.Login;
import com.uaspbp.rentalmotor.R;

import java.security.GeneralSecurityException;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText txtFullName, txtEmail, txtPassword;
    private Button registerUser;
    private Intent intent = null;
    private String AESPassword = "password", ePassword, userID, nama, email, password;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                if(txtEmail.getText().toString().isEmpty())
                {
                    txtEmail.setError("Email Harus Diisi!");
                    txtEmail.requestFocus();
                }
                else if (txtPassword.getText().toString().isEmpty())
                {
                    txtPassword.setError("Password Harus Diisi!");
                    txtPassword.requestFocus();
                }
                else if(!txtEmail.getText().toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"))
                {
                    txtEmail.setError("Email harus Sesuai");
                    txtEmail.requestFocus();
                    return;
                }
                else if(txtPassword.length() <6){
                    txtPassword.setError("Password kurang dari 6!");
                    txtPassword.requestFocus();
                    return;
                }
                else if(txtFullName.getText().toString().isEmpty())
                {
                    txtFullName.setError("Full Name harus Diisi!");
                    txtFullName.requestFocus();
                }
                else{
                    registerUser();
                }
            }
        });
        txtFullName = (EditText) findViewById(R.id.fullname);
        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        nama = txtFullName.getText().toString();
        email = txtEmail.getText().toString();
        //password = txtPassword.getText().toString();
    }

    private void registerUser(){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String name = txtFullName.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            // SAVE DATA KE REALTIME DTABASE
                            ePassword = encrypt(AESPassword, password);
                            userID = firebaseUser.getUid();
                            databaseReference.child(userID).child("name").setValue(name);
                            databaseReference.child(userID).child("email").setValue(email);
                            databaseReference.child(userID).child("password").setValue(ePassword);
                            databaseReference.child(userID).child("alamat").setValue("-");
                            databaseReference.child(userID).child("no_telp").setValue("-");


                            Toast.makeText(Register.this, "Register Sukses", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Register.this, "Register Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private String encrypt(String AESPassword, String message) {
        String encryptedMsg = null;
        try {
            encryptedMsg = AESCrypt.encrypt(AESPassword, message);
        }catch (GeneralSecurityException e){
            e.printStackTrace();
        }
        return encryptedMsg;
    }

}
