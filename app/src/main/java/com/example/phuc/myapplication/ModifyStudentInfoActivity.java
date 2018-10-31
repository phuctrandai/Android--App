package com.example.phuc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ModifyStudentInfoActivity extends AppCompatActivity {

    public static final int RESULT_MODIFIED = 5;

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
        setContentView(R.layout.activity_modify_student_info);
        getSupportActionBar().setTitle("Chỉnh sửa thông tin");
        setView();
        setAction();

        Bundle bundle = getIntent().getExtras();
        showStudentDetail(bundle);
    }

    private void setAction() {
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

                Date temp = new Date(spnDay.getSelectedItemPosition() + 1, spnMonth.getSelectedItemPosition() + 1, spnYear.getSelectedItemPosition() + 1990);
                String birthDate = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("vie", "VN")).format(temp);
                bundleResult.putString("birthDate", birthDate);

                intentResult.putExtras(bundleResult);
                setResult(RESULT_MODIFIED, intentResult);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                ModifyStudentInfoActivity.super.onBackPressed();
            }
        });
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
    }

    private void showStudentDetail(Bundle bundle) {
        edtId.append(bundle.getString("id"));
        edtName.append(bundle.getString("name"));
        edtAge.append(bundle.getInt("age") + "");
        edtAddress.append(bundle.getString("address"));

        String[] sex;
        if (bundle.getBoolean("sex"))
            sex = new String[]{"Nam", "Nữ"};
        else
            sex = new String[]{"Nữ", "Nam"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sex);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnSex.setAdapter(adapter);

        // Set day
        Date birthDate = new Date();
        try {
            birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(bundle.getString("birthDate"));
            Log.d(this.getClass().toString(), "Format date success: " + birthDate);
        } catch (ParseException e) {
            Log.d(this.getClass().toString(), "Format date error: " + e.getMessage());
        }
        ArrayList<String> day = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            day.add(String.valueOf(i));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, day);
        spnDay.setAdapter(adapter);
        spnDay.setSelection(birthDate.getDay() + 27);
        Log.d(this.getClass().toString(), birthDate.getDay() + "");

        // Set month
        ArrayList<String> month = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            month.add(String.valueOf(i));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, month);
        spnMonth.setAdapter(adapter);
        spnMonth.setSelection(birthDate.getMonth());
        Log.d(this.getClass().toString(), birthDate.getMonth() + "");

        // Set year
        ArrayList<String> year = new ArrayList<>();
        for (int i = 1990; i <= 2018; i++) {
            year.add(String.valueOf(i));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, year);
        spnYear.setAdapter(adapter);
        spnYear.setSelection(birthDate.getYear() - 90);
        Log.d(this.getClass().toString(), birthDate.getYear() - 90 + "");
    }
}
