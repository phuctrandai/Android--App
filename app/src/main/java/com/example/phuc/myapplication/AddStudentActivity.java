package com.example.phuc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddStudentActivity extends AppCompatActivity {
    private EditText edtId;
    private EditText edtName;
    private EditText edtAge;
    private EditText edtBirthDate;
    private EditText edtAddress;
    private Spinner spnSex;

    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        spnSex = findViewById(R.id.spnSex);
        String[] sex = new String[]{"Nam", "Nữ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sex);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnSex.setAdapter(adapter);

        setActon();
    }

    private void setActon() {
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStudentActivity.super.onBackPressed();
            }
        });
    }
}
