package com.uaspbp.rentalmotor.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uaspbp.rentalmotor.MainActivity;
import com.uaspbp.rentalmotor.R;
import com.uaspbp.rentalmotor.Register.Register;

public class Login extends AppCompatActivity {

    private Button register, login;
    private EditText txtPassword, txtEmail;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        register = (Button) findViewById(R.id.registerUser);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        login = (Button) findViewById(R.id.loginUser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                else{
                    login();
                }

            }
        });

        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
    }

    private void login(){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Please check your email!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Login.this, "Failed to login!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
