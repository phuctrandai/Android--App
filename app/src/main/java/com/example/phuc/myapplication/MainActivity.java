package com.example.phuc.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phuc.myapplication.Adapter.StudentAdapter;
import com.example.phuc.myapplication.Entity.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final int SHOW_DETAIL_STUDENT_REQUEST = 3;

    private ListView lvDSSinhVien;
    private ArrayList<Student> dsSinhVien;
    private StudentAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customActionBar();

        hienThiDsSinhVien();
    }

    public void customActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách sinh viên");

    }

    public void hienThiDsSinhVien() {
        dsSinhVien = Student.getList();

        lvDSSinhVien = (ListView) findViewById(R.id.lvDSSinhVien);

        arrayAdapter = new StudentAdapter(this, dsSinhVien);
        lvDSSinhVien.setAdapter(arrayAdapter);

        lvDSSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);

                Date temp = dsSinhVien.get(position).getBirthDate();
                String birthDate = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("vie", "VN")).format(temp);

                Bundle bundle = new Bundle();
                bundle.putInt("stuPos", position);
                bundle.putString("id", dsSinhVien.get(position).getId());
                bundle.putString("name", dsSinhVien.get(position).getName());
                bundle.putInt("age", dsSinhVien.get(position).getAge());
                bundle.putBoolean("sex", dsSinhVien.get(position).isMale());
                bundle.putString("birthDate", birthDate);
                bundle.putString("address", dsSinhVien.get(position).getAddress());
                intent.putExtras(bundle);

                startActivityForResult(intent, SHOW_DETAIL_STUDENT_REQUEST);
            }
        });

        lvDSSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Thong bao!");
                builder.setMessage("Ban co muon xoa '" + dsSinhVien.get(position).getName() + "' khong ?");

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Da xoa " + dsSinhVien.get(position).getName(), Toast.LENGTH_SHORT).show();
                        dsSinhVien.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SHOW_DETAIL_STUDENT_REQUEST) {
            if (resultCode == ModifyStudentInfoActivity.RESULT_MODIFIED) {
                Bundle bundle = data.getExtras();
                int position = bundle.getInt("stuPos");
                Date birthDate = null;
                try {
                    birthDate = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("vie", "VN")).parse(bundle.getString("birthDate"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dsSinhVien.get(position).setMale(bundle.getBoolean("sex"));
                dsSinhVien.get(position).setId(bundle.getString("id"));
                dsSinhVien.get(position).setName(bundle.getString("name"));
                dsSinhVien.get(position).setAge(bundle.getInt("age"));
                dsSinhVien.get(position).setAddress(bundle.getString("address"));
                dsSinhVien.get(position).setBirthDate(birthDate);
                arrayAdapter.notifyDataSetChanged();
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Notification");
        builder.setMessage("Are you sure to exit ?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnItemAbout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("About");
            builder.setMessage("Copyright by Phuc You");
            builder.create().show();
        }
        if (item.getItemId() == R.id.mnItemSearch) {

        }
        return super.onOptionsItemSelected(item);
    }
}
