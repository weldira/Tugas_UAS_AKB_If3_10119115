package com.example.tugas_uas_akb_if3_10119115;
//nim : 10119115
//nama : Weldira Tarigan
//kelas : if-3

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In_Activity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Sign_In_Activity.this, MainActivity.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("Email harus diisi!");

                } else if (TextUtils.isEmpty(password)) {
                    inputPassword.setError("Password harus diisi!");
                } else if (password.length() < 6) {
                    inputPassword.setError("Password minimal 6 karakter!");
                } else {
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Sign_In_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Sign_In_Activity.this, "Sign In berhasil!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Sign_In_Activity.this, MainActivity.class));
                            } else {
                                Toast.makeText(Sign_In_Activity.this, "Sign In gagal!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_In_Activity.this, Sign_Up_Activity.class));
            }
        });
    }
}