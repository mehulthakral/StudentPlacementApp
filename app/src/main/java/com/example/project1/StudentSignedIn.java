package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class StudentSignedIn extends AppCompatActivity {


    private static final String EXTRA_CONTENT = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_IMAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DATE="EXTRA_DATE";
    private static final String EXTRA_PHONENUMBER="PHONE NUMBER";
    private static final String EXTRA_MAIL="MAIL";
    SQLiteDatabase db;
    ListView list;
    String tag="ERROR";

    String srn;
    TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signed_in);

        Intent i = getIntent();
        srn = i.getStringExtra("srn");
        int imagevalue = R.drawable.ic_launcher_background;
        db = openOrCreateDatabase("Placements", Context.MODE_PRIVATE, null);

        greeting = (TextView) findViewById(R.id.student_SRN_view);
        greeting.setText("SRN:" + srn);

        Cursor cr1 = db.rawQuery("SELECT * FROM Students WHERE SRN='"+srn+"';", null);
        if(cr1.moveToFirst()) {
            String SEM = cr1.getString(4);
            String CGPA = cr1.getString(3);
            Log.d("ERROR", SEM + CGPA);
            greeting=(TextView)findViewById(R.id.student_SEM_PHONENUMBER);
            greeting.setText("SEM:"+SEM+"\n"+"CGPA:"+CGPA);
        }

        ListView list=(ListView)findViewById(R.id.list_for_student);
        int maxvalue;
        maxvalue=getListValue();
        String[] Web1 = new String[maxvalue];
        Integer[] Id1 = new Integer[maxvalue];
        String[] Date1= new String[maxvalue];
        String[] Email1= new String[maxvalue];
        String[] Phone1= new String[maxvalue];

        generateData(Web1,Id1,Date1,Email1,Phone1,maxvalue);

        final String[] WebF=Web1.clone();
        final String[] DateF=Date1.clone();
        final String[] EmailF=Email1.clone();
        final String[] PhoneF=Phone1.clone();
        final Integer[] IdF=Id1.clone();

        singlelistadap adapter=new singlelistadap(StudentSignedIn.this,Web1,Id1,Date1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,"You Have Clicked"+WebF[position],Toast.LENGTH_LONG).show();
                Intent intent=new Intent(StudentSignedIn.this, Admin_DetailActivity1.class);
                intent.putExtra(EXTRA_CONTENT, WebF[position]);
                intent.putExtra(EXTRA_IMAGE,IdF[position]);
                intent.putExtra(EXTRA_MAIL,EmailF[position]);
                intent.putExtra(EXTRA_DATE,DateF[position]);
                intent.putExtra(EXTRA_PHONENUMBER,PhoneF[position]);
                //Log.d("PHONENUMBER",PhoneF[position]);
                startActivity(intent);
            }
        });


        Button signout=(Button)findViewById(R.id.student_signed_out);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void generateData(String[] Web,Integer[] Id,String[] Date,String[] Email,String[] Phone,int maxvalue)
    {
        String tag="ERROR";
        SQLiteDatabase db=openOrCreateDatabase("Placements",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Events1(ID INTEGER PRIMARY KEY AUTOINCREMENT,information varchar,ident int,date varchar,email varchar,phone varchar);");
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
}
