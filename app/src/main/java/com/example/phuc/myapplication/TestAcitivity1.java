package com.example.phuc.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestAcitivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acitivity1);
    }

    public void login(View view) {
        EditText un = findViewById(R.id.userName1);
        EditText pw = findViewById(R.id.password);

        Toast.makeText(this, "Login successful !\nWelcome " + un.getText(), Toast.LENGTH_SHORT).show();
    }
}
