package com.brilicaservices.databaserecyclerviewintent;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StudentInfo extends  AppCompatActivity  {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        TextView information1 = (TextView) findViewById(R.id.student_info_textview1);
        TextView information2 = (TextView) findViewById(R.id.student_info_textview2);
        TextView information3 = (TextView) findViewById(R.id.student_info_textview3);
        TextView information4 = (TextView) findViewById(R.id.student_info_textview4);
        Intent intent=getIntent();
        String name,college,address;
        long phone;
        name=intent.getStringExtra("Name");
        college=intent.getStringExtra("College");
        phone=intent.getLongExtra("PhoneNo",0);
        address=intent.getStringExtra("Address");
        information1.setText("Student Name : "+name);
        information2.setText("College Name : "+college);
        information3.setText("Student PhoneNo. : "+String.valueOf(phone));
        information4.setText("Student Address : "+address);

    }
    }
