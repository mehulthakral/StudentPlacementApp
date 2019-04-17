package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    Button AdminLogin,StudentLogin,Register;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AdminLogin=(Button)findViewById(R.id.button);
        StudentLogin=(Button)findViewById(R.id.button1);
        Register=(Button)findViewById(R.id.button2);
        db=openOrCreateDatabase("Placements", Context.MODE_PRIVATE,null);
        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("CREATE TABLE IF NOT EXISTS Admins(LoginId Varchar,Password Varchar);");
               Cursor c=db.rawQuery("SELECT * FROM Admins",null);
               if(c.getCount()==0)
               {
                   db.execSQL("INSERT INTO Admins (LoginId,Password) Values('Admin1','9876543210');");
               }
                Intent Adminpage=new Intent(getApplicationContext(),AdminLogin.class);
                startActivity(Adminpage);
            }
        });

        StudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("CREATE TABLE IF NOT EXISTS Students(SRN Varchar,Password Varchar,Age Varchar,CGPA Varchar,Semester Varchar);");
                Intent StudentPage=new Intent(getApplicationContext(),StudentActivity.class);
                startActivity(StudentPage);
            }
        });
    }
}
