package com.example.project1;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Admin_DetailActivity1 extends AppCompatActivity {

    private static final String EXTRA_CONTENT = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_IMAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DATE="EXTRA_DATE";
    private static final String EXTRA_PHONENUMBER="PHONE NUMBER";
    private static final String EXTRA_MAIL="MAIL";
    private TextView message;
    private View coloredBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        Intent i=getIntent();


        String messageExtra = i.getStringExtra(EXTRA_CONTENT);
        message = (TextView) findViewById(R.id.lbl_message_body);
        message.setText(messageExtra);

        int drawableResourceExtra = i.getIntExtra(EXTRA_IMAGE, 0);

        coloredBackground = findViewById(R.id.imv_colored_background);
        coloredBackground.setBackgroundResource(
                drawableResourceExtra
        );

        String DateofEvent = i.getStringExtra(EXTRA_DATE);
        message = (TextView) findViewById(R.id.lbl_date_and_time_header);
        message.setText(DateofEvent);

        String PhoneNumberofEvent= i.getStringExtra(EXTRA_PHONENUMBER);
        String MailofEvent = i.getStringExtra(EXTRA_MAIL);


        FloatingActionButton fab = findViewById(R.id.detailed_activity_fabulous_gmail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        FloatingActionButton fab1=findViewById(R.id.detailed_activity_fabulous_phonenumber);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

}