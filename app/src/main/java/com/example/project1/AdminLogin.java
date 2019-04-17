package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity{

    SQLiteDatabase db;
    EditText AdminName,AdminPassword;
    Button AdminLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        AdminLogin=(Button)findViewById(R.id.button_admin_login);
        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(v);
            }
        });
    }
    void checkLogin(View v)
    {
        db=openOrCreateDatabase("Placements", Context.MODE_PRIVATE,null);
        Cursor cr=db.rawQuery("SELECT * FROM Admins",null);
        cr.moveToFirst();
        String Name;
        String Password;
        AdminName=(EditText)findViewById(R.id.login_Admin_Name);
        AdminPassword=(EditText)findViewById(R.id.login_Admin_password);
        Name=AdminName.getText().toString();
        Password=AdminPassword.getText().toString();
        if(Name.equals(cr.getString(0)) && Password.equals(cr.getString(1)))
        {
           Intent nextPage;
           nextPage= new Intent(getApplicationContext(),Admin.class);
           startActivity(nextPage);
        }
        else
        {
            Toast.makeText(getApplicationContext(),cr.getString(0)+Name+cr.getString(1)+Password,Toast.LENGTH_LONG).show();
        }
    }
}
