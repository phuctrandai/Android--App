package com.example.phuc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class StudentDetailActivity extends AppCompatActivity {

    private TextView tvId;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvSex;
    private TextView tvBirthDate;
    private TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        showStudentDetail(bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_detail_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnItemModify) {
            Intent intent = new Intent(StudentDetailActivity.this, ModifyStudentInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("id", tvId.getText().toString());
            bundle.putString("name", tvName.getText().toString());
            bundle.putInt("age", Integer.parseInt(tvAge.getText().toString()));
            bundle.putBoolean("sex", Boolean.parseBoolean(tvSex.getText().toString()));
            bundle.putString("birthDate", tvBirthDate.getText().toString());
            bundle.putString("address", tvAddress.getText().toString());
            intent.putExtras(bundle);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showStudentDetail(Bundle bundle) {
        tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvSex = findViewById(R.id.tvSex);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvAddress = findViewById(R.id.tvAddress);

        tvId.setText(bundle.getString("id"));
        tvName.setText(bundle.getString("name"));
        tvAge.setText(bundle.getInt("age") + "");
        tvSex.setText(bundle.getBoolean("sex") + "");
        tvBirthDate.setText(bundle.getString("birthDate"));
        tvAddress.setText(bundle.getString("address"));
    }
}
