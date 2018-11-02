package com.example.phuc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class StudentDetailActivity extends AppCompatActivity {
    static final int MODIFY_STUDENT_INFO_REQUEST = 1;

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
        overridePendingTransition(R.anim.fade_anim_enter, R.anim.fade_anim_exit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        showStudentDetail(bundle);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
            boolean sex = tvSex.getText().equals("Nam") ? true : false;

            bundle.putString("id", tvId.getText().toString());
            bundle.putString("name", tvName.getText().toString());
            bundle.putInt("age", Integer.parseInt(tvAge.getText().toString()));
            bundle.putString("birthDate", tvBirthDate.getText().toString());
            bundle.putString("address", tvAddress.getText().toString());
            bundle.putBoolean("sex", sex);
            intent.putExtras(bundle);

            startActivityForResult(intent, MODIFY_STUDENT_INFO_REQUEST);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == MODIFY_STUDENT_INFO_REQUEST) {
            if (resultCode == ModifyStudentInfoActivity.RESULT_MODIFIED) {
                Bundle bundle = data.getExtras();
                String sex = bundle.getBoolean("sex") ? "Nam" : "Nữ";
                tvId.setText(bundle.getString("id"));
                tvName.setText(bundle.getString("name"));
                tvAge.setText(bundle.getInt("age") + "");
                tvSex.setText(sex);
                tvBirthDate.setText(bundle.getString("birthDate"));
                tvAddress.setText(bundle.getString("address"));
                setResult(resultCode, data);
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showStudentDetail(Bundle bundle) {
        tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvSex = findViewById(R.id.tvSex);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvAddress = findViewById(R.id.tvAddress);

        String sex = bundle.getBoolean("sex") ? "Nam" : "Nữ";
        tvSex.setText(sex);
        tvId.setText(bundle.getString("id"));
        tvName.setText(bundle.getString("name"));
        tvAge.setText(bundle.getInt("age") + "");
        tvBirthDate.setText(bundle.getString("birthDate"));
        tvAddress.setText(bundle.getString("address"));
        getSupportActionBar().setTitle("Xem chi tiết");
    }
}
