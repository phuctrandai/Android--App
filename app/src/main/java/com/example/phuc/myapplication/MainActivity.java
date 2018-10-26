package com.example.phuc.myapplication;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuc.myapplication.Adapter.StudentAdapter;
import com.example.phuc.myapplication.Entity.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvDSSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hienThiDsSinhVien();
    }

    public void hienThiDsSinhVien() {
        final ArrayList<Student> dsSinhVien = Student.getList();

        lvDSSinhVien = (ListView) findViewById(R.id.lvDSSinhVien);

        final StudentAdapter arrayAdapter = new StudentAdapter(this, dsSinhVien);
        lvDSSinhVien.setAdapter(arrayAdapter);

        lvDSSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("id", dsSinhVien.get(position).getId());
                bundle.putString("name", dsSinhVien.get(position).getName());
                bundle.putInt("age", dsSinhVien.get(position).getAge());
                bundle.putBoolean("sex", dsSinhVien.get(position).isMale());
                bundle.putString("birthDate", dsSinhVien.get(position).getBirthDate().toLocaleString());
                bundle.putString("address", dsSinhVien.get(position).getAddress());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        lvDSSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Thong bao!");
                builder.setMessage("Ban co muon xoa '" + dsSinhVien.get(position) + "' khong ?");

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
                return false;
            }
        });
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
        if (item.getItemId() == R.id.mnItemSetting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
