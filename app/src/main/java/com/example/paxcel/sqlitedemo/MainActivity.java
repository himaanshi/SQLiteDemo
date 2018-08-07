package com.example.paxcel.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter arrayAdapter;
    ListView listView;
    ArrayList<String> arrayList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.list_data);
        arrayList=new ArrayList<>();
        arrayList = dbHelper.getAllCotacts();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        Log.e("data", arrayList.toString());

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddDataActivity.class));
                finish();
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        arrayList = dbHelper.getAllCotacts();
        listView.setAdapter(arrayAdapter);

    }
}
