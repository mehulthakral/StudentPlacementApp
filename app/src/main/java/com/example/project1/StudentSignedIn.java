package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StudentSignedIn extends AppCompatActivity {

    String srn;
    TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signed_in);

        Intent i = getIntent();
        srn = i.getStringExtra("srn");
        greeting = (TextView)findViewById(R.id.greeting);
        greeting.setText("Welcome " + srn + "!");
    }
}
