package com.example.rememberme.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rememberme.R;
import com.example.rememberme.UtilityClasses.DBManager;


import static com.example.rememberme.UtilityClasses.Tags.USER_NAME;
import static com.example.rememberme.UtilityClasses.Tags.USER_PASSWORD;
import static com.example.rememberme.UtilityClasses.Tags.USER_saveLogin;

public class LoginActivity extends AppCompatActivity {

    EditText edittextname, edittextpassword;
    CheckBox checkBox;
    Button login;
    Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initViews();
    }

    private void initViews() {
        edittextname = findViewById(R.id.name);
        edittextpassword = findViewById(R.id.password);
        checkBox = findViewById(R.id.checkbox);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate())
                    login();
            }
        });
        saveLogin = DBManager.getBoolPrefs(LoginActivity.this, USER_saveLogin);
        if (saveLogin) {
            edittextname.setText(DBManager.getStringPrefs(LoginActivity.this, USER_NAME));
            edittextpassword.setText(DBManager.getStringPrefs(LoginActivity.this, USER_PASSWORD));
            checkBox.setChecked(true);
        }
    }

    public void doSomethingElse() {
        Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(LoginActivity.this.this, LoginActivity.class));
//        LoginActivity.this.finish();
    }


    private void login() {
        String name = edittextname.getText().toString();
        String password = edittextpassword.getText().toString();
        if (name.equals("naeem") && password.equals("123")) {
            if (checkBox.isChecked()) {
                DBManager.setStringPrefs(LoginActivity.this, USER_NAME, name);
                DBManager.setStringPrefs(LoginActivity.this, USER_PASSWORD, password);
                DBManager.setBoolPrefs(LoginActivity.this, USER_saveLogin, true);
            } else {
                DBManager.removeAllPreferencesData(LoginActivity.this);
                Toast.makeText(LoginActivity.this, "this", Toast.LENGTH_LONG).show();
            }
            doSomethingElse();
        }
    }

    private boolean isValidate() {
        String name = edittextname.getText().toString();
        String password = edittextpassword.getText().toString();
        if (name.equals("")) {
            edittextname.setError("Please Enter Name");
            return false;
        }
        if (password.equals("")) {
            edittextpassword.setError("Please Enter Password");
            return false;
        }
        return true;
    }
}

