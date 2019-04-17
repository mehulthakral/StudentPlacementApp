package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Admin_Event extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText info,date,email,phone;
    String type;
    Button add;
    SQLiteDatabase db;
    public int imagevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__event);

        info = (EditText)findViewById(R.id.eventInfo);
        date = (EditText)findViewById(R.id.eventDate);
        email = (EditText)findViewById(R.id.eventEmail);
        phone = (EditText)findViewById(R.id.eventPhone);
        add = (Button)findViewById(R.id.eventAdd);

        Spinner spinner = (Spinner) findViewById(R.id.eventType);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.events_type, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db=openOrCreateDatabase("Placements", Context.MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS Events1(ID INTEGER PRIMARY KEY AUTOINCREMENT,information varchar,ident int,date varchar,email varchar,phone varchar);");
                if(type.equals("Placement"))
                    imagevalue = R.drawable.placement_final;
                else if(type.equals("Internship"))
                    imagevalue = R.drawable.internship_final;
                else if(type.equals("Workshop"))
                    imagevalue = R.drawable.workshop_final;
                else if(type.equals("Talk"))
                    imagevalue = R.drawable.talk_final;
                else
                    imagevalue = R.drawable.placement_final;

                db.execSQL("INSERT INTO Events1(information,ident,date,email,phone)VALUES('"+ info.getText() +"',"+imagevalue+",'"+ date.getText() +"','"+ email.getText() +"','"+ phone.getText() +"');");
                Toast.makeText(Admin_Event.this, "Event successfully added!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),Admin.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = (String) parent.getItemAtPosition(position);
        //Log.i("Error",type);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        type = "Placement";
    }
}

