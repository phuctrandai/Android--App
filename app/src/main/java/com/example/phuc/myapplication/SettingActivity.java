package com.example.phuc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Spinner spnNational = findViewById(R.id.spnNational);

        ArrayList<String> list = new ArrayList<>();
        list.add("Việt nam");
        list.add("Mỹ");
        list.add("Pháp");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnNational.setAdapter(adapter);
    }
}
