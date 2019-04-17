package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class Admin extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    private static final String EXTRA_CONTENT = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_IMAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DATE="EXTRA_DATE";
    private static final String EXTRA_PHONENUMBER="PHONE NUMBER";
    private static final String EXTRA_MAIL="MAIL";
    SQLiteDatabase db;
    ListView list;
    String tag="ERROR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        list=(ListView)findViewById(R.id.list);


        int imagevalue=R.drawable.ic_launcher_background;
        db=openOrCreateDatabase("Placements",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Events1(ID INTEGER PRIMARY KEY AUTOINCREMENT,information varchar,ident int,date varchar,email varchar,phone varchar);");
        //Log.d(tag,"HERE DATABASE TABLE");
        db.execSQL("DELETE FROM Events1");
        db.execSQL("INSERT INTO Events1(information,ident,date,email,phone)VALUES('THESE ARE REPLICATES OF EVENTS THAT WE ARE SUPPOSED TO BE MIMICING BUT WE ARE STILL TRYING OUT SOME BASIC STUFF',"+imagevalue+",'22/04/1999','hemanthvenki910@gmail.com','9449398414');");
        db.execSQL("INSERT INTO Events1(information,ident,date,email,phone)VALUES('THESE ARE REPLICATES OF EVENTS',"+imagevalue+",'22/04/1999','hemanthvenki910@gmail.com','9449398414');");
        db.execSQL("INSERT INTO Events1(information,ident,date,email,phone)VALUES('THESE ARE REPLICATES OF EVENTS',"+imagevalue+",'22/04/1999','hemanthvenki910@gmail.com','9449398414');");
        db.execSQL("INSERT INTO Events1(information,ident,date,email,phone)VALUES('THESE ARE REPLICATES OF EVENTS',"+imagevalue+",'22/04/1999','hemanthvenki910@gmail.com','9449398414');");
        db.execSQL("INSERT INTO Events1(information,ident,date,email,phone)VALUES('THESE ARE REPLICATES OF EVENTS',"+imagevalue+",'22/04/1999','hemanthvenki910@gmail.com','9449398414');");

        int maxvalue;
        maxvalue=getListValue();
        String[] Web1 = new String[maxvalue];
        Integer[] Id1 = new Integer[maxvalue];
        String[] Date1= new String[maxvalue];
        String[] Email1= new String[maxvalue];
        String[] Phone1= new String[maxvalue];

        //Log.d(tag,"HERE DATABASE TABLE");

        generateData(Web1,Id1,Date1,Email1,Phone1,maxvalue);

        final String[] WebF=Web1.clone();
        final String[] DateF=Date1.clone();
        final String[] EmailF=Email1.clone();
        final String[] PhoneF=Phone1.clone();
        final Integer[] IdF=Id1.clone();


        singlelistadap adapter=new singlelistadap(Admin.this,Web1,Id1,Date1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,"You Have Clicked"+WebF[position],Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Admin.this, Admin_DetailActivity1.class);
                intent.putExtra(EXTRA_CONTENT, WebF[position]);
                intent.putExtra(EXTRA_IMAGE,IdF[position]);
                intent.putExtra(EXTRA_MAIL,EmailF[position]);
                intent.putExtra(EXTRA_DATE,DateF[position]);
                intent.putExtra(EXTRA_PHONENUMBER,PhoneF[position]);
                //Log.d("PHONENUMBER",PhoneF[position]);
                startActivity(intent);
            }
        });

        //code for the Navigation bar
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open,R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(Admin.this, "My Account",Toast.LENGTH_SHORT).show();
                    case R.id.settings:
                        Toast.makeText(Admin.this, "Settings",Toast.LENGTH_SHORT).show();
                    case R.id.mycart:
                        Toast.makeText(Admin.this, "My Cart",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }
            }
        });


        FloatingActionButton fltbutton=(FloatingActionButton)findViewById(R.id.admin_addevent_fabulous);
        fltbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin.this,Admin_Event.class);
                startActivity(i);
            }
        });
    }



    void generateData(String[] Web,Integer[] Id,String[] Date,String[] Email,String[] Phone,int maxvalue)
    {
        String tag="ERROR";
        SQLiteDatabase db=openOrCreateDatabase("Placements",Context.MODE_PRIVATE,null);

        Cursor cr=db.rawQuery("SELECT * FROM Events1 ORDER BY ID DESC",null);
        int i=0;
        while(cr.moveToNext() && i<maxvalue)
        {
            Web[i]=cr.getString(1);
            Id[i]= Integer.valueOf(cr.getString(2));
            Date[i]=cr.getString(3);
            Email[i]=cr.getString(4);
            Phone[i]=cr.getString(5);
           // Log.d(tag,cr.getString(5));
            i++;
        }
        //Log.d(tag,"HERE");
    }
    public int getListValue()
    {
        SQLiteDatabase db=openOrCreateDatabase("Placements", Context.MODE_PRIVATE,null);
        Cursor cr=db.rawQuery("SELECT * FROM Events1",null);
        //Log.d("EROOROOR","HEY IM HERE");
        return(cr.getCount());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
