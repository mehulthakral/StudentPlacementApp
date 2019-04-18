package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity {

    TextView srn,password;
    Button login,register;
    SQLiteDatabase db;
    String loginSRN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        srn = (TextView)findViewById(R.id.loginSRN);
        password = (TextView)findViewById(R.id.loginPassword);
        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.studentLoginRegister);
        db=openOrCreateDatabase("Placements", Context.MODE_PRIVATE,null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(srn.getText().toString().trim().length() == 0 || password.getText().toString().trim().length() == 0){
                    Toast.makeText(StudentLogin.this, "Please enter SRN and Password", Toast.LENGTH_LONG).show();
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM Students WHERE SRN='"+srn.getText()+"' and Password='"+password.getText()+"'", null);
                if(c.moveToFirst()){
                    Intent i = new Intent(getApplicationContext(),StudentSignedIn.class);
                    i.putExtra("srn",srn.getText().toString());
                    Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
                else {
                    Toast.makeText(StudentLogin.this, "Invalid Credentials! Try Again", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StudentActivity.class);
                i.putExtra("srn",srn.getText());
                i.putExtra("password",password.getText());
                startActivity(i);
            }
        });

    }
    @Override
    protected void onPause() {
        try {
            super.onPause();
            srn.setText("");
            password.setText("");
        }
        catch(Exception e)
        {
            Log.d("ERROR WHILE PAUSE",e.toString());
        }
    }
}
