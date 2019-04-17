package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button register;
    EditText srn,password,age,cgpa,sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        db=openOrCreateDatabase("Placements", Context.MODE_PRIVATE,null);
        register = (Button)findViewById(R.id.studentRegister);
        srn = (EditText)findViewById(R.id.studentSRN);
        password = (EditText)findViewById(R.id.studentPassword);
        age = (EditText)findViewById(R.id.studentAge);
        cgpa = (EditText)findViewById(R.id.studentCGPA);
        sem = (EditText)findViewById(R.id.studentSem);

        Intent recieve = getIntent();
        if(recieve.getStringExtra("srn")!="" && recieve.getStringExtra("password")!=""){
            srn.setText(recieve.getStringExtra("srn"));
            password.setText(recieve.getStringExtra("password"));
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO Students Values('" + srn.getText() +"','"+ password.getText() +"','"+ age.getText() +"','"+ cgpa.getText() +"','"+ sem.getText()+"');");
                Intent i = new Intent(getApplicationContext(),StudentSignedIn.class);
                i.putExtra("srn",srn.getText().toString());
                Toast.makeText(StudentActivity.this, "You are successfully registered!", Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }
}