package com.example.phuc.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ModifyStudentInfoActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_modify_student_info);

        Bundle bundle = getIntent().getExtras();
        showStudentDetail(bundle);

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
                ModifyStudentInfoActivity.super.onBackPressed();
            }
        });
    }

    private void showStudentDetail(Bundle bundle) {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtBirthDate = findViewById(R.id.edtBirthDate);
        edtAddress = findViewById(R.id.edtAddress);
        spnSex = findViewById(R.id.spnSex);

        edtId.setText(bundle.getString("id"));
        edtName.setText(bundle.getString("name"));
        edtAge.setText(bundle.getInt("age") + "");
        edtBirthDate.setText(bundle.getString("birthDate"));
        edtAddress.setText(bundle.getString("address"));

        String[] sex;
        if (bundle.getBoolean("sex"))
            sex = new String[]{"Nam", "Nữ"};
        else
            sex = new String[]{"Nữ", "Nam"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sex);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnSex.setAdapter(adapter);
    }
}
