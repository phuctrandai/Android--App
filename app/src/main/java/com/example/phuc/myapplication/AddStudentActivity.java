package com.example.phuc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {
    public static final int RESULT_ADDED = 3;

    private EditText edtId;
    private EditText edtName;
    private EditText edtAge;
    private EditText edtAddress;
    private Spinner spnSex;
    private Spinner spnDay;
    private Spinner spnMonth;
    private Spinner spnYear;

    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        overridePendingTransition(R.anim.slide_anim_enter, R.anim.slide_anim_exit);

        spnSex = findViewById(R.id.spnSex);
        String[] sex = new String[]{"Nam", "Nữ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sex);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnSex.setAdapter(adapter);

        setView();
        setActon();
    }

    private void setView() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtAddress = findViewById(R.id.edtAddress);
        spnSex = findViewById(R.id.spnSex);
        spnDay = findViewById(R.id.spnDay);
        spnMonth = findViewById(R.id.spnMonth);
        spnYear = findViewById(R.id.spnYear);

        // Set data for spinner day
        ArrayList<String> day = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            day.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, day);
        spnDay.setAdapter(adapter);

        // Set data for spinner month
        ArrayList<String> month = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            month.add(String.valueOf(i));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, month);
        spnMonth.setAdapter(adapter);

        // Set data for spinner year
        ArrayList<String> year = new ArrayList<>();
        for (int i = 1990; i <= 2018; i++) {
            year.add(String.valueOf(i));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, year);
        spnYear.setAdapter(adapter);
    }

    private void setActon() {
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                Bundle bundleResult = new Bundle();
                bundleResult.putString("id", edtId.getText().toString());
                bundleResult.putString("name", edtName.getText().toString());
                bundleResult.putInt("age", Integer.parseInt(edtAge.getText().toString()));
                bundleResult.putString("address", edtAddress.getText().toString());
                boolean sex = spnSex.getSelectedItem().toString().equals("Nam") ? true : false;
                bundleResult.putBoolean("sex", sex);

                String day = spnDay.getSelectedItem().toString();
                String month = spnMonth.getSelectedItem().toString();
                String year = spnYear.getSelectedItem().toString();
                String birthDate = day + "/" + month + "/" + year;
                bundleResult.putString("birthDate", birthDate);

                intentResult.putExtras(bundleResult);
                setResult(RESULT_ADDED, intentResult);
                finish();
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
