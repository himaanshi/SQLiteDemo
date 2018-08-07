package com.example.paxcel.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {
    EditText etName, etPhone, etStreet, etCity;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etStreet = findViewById(R.id.et_street);
        etCity = findViewById(R.id.et_city);

        button=findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick", "here1");

            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("data", "here");
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String street = etStreet.getText().toString();
                String city = etCity.getText().toString();

                DBHelper dbHelper = new DBHelper(AddDataActivity.this);
                dbHelper.getWritableDatabase();

                boolean value = dbHelper.insertContact(name, phone, street, city);

                if (value) {
                    Toast.makeText(AddDataActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddDataActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(AddDataActivity.this, "Data failure", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


}


