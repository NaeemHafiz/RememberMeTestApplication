package com.example.rememberme.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rememberme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button btnsignup;
    CheckBox firebasecheckbox;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("ProgressDialog bar example");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //Get Firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();

        initViews();
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.firebaseemail);
        editTextPassword = findViewById(R.id.firebasepassword);
        firebasecheckbox = findViewById(R.id.firebasecheckbox);
        btnsignup = findViewById(R.id.firebasesignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate())
                    registerUser();
            }
        });

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.hide();
                if (!task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Authentication success." + task.getException(),
                            Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    finish();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDialog.hide();
    }

    private boolean isValidate() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please Enter Email");
            editTextEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please Enter Password");
            return false;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Password should be at lease 6 characters");
            editTextPassword.requestFocus();
            return false;
        }
        return true;
    }
}
