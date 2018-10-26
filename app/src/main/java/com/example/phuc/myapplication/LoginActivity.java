package com.example.phuc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText txtUserName, txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName = findViewById(R.id.txt_UserName);
        txtPassword = findViewById(R.id.txt_Password);
        btnLogin = findViewById(R.id.btn_Login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUserName.getText().toString().equals("admin") &&
                        txtPassword.getText().toString().equals("123")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", txtUserName.getText().toString());
                    intent.putExtras(bundle);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
