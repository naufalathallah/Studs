package com.example.studs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    private Button btHome, btRecord, btBooked, btProfile, btLogout;
    private DBManager dbManager;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.COLUMN_USER_NAME};

    final int[] to = new int[] { R.id.username};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbManager = new DBManager(this);
        dbManager.open();
        TextView textview = findViewById(R.id.unameuser);
        textview.setText(Preferences.getLoggedInUser(getBaseContext()));


        //pertombolan
        btHome = (Button) findViewById(R.id.bthome);
        btRecord = (Button) findViewById(R.id.btrecord);
        btBooked = (Button) findViewById(R.id.btbooked);
        btProfile = (Button) findViewById(R.id.btprofile);
        btLogout = (Button) findViewById(R.id.btLogout);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Profile.this, MainActivity.class);
                startActivity(intents);
            }
        });
        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Profile.this, Record.class);
                startActivity(intents);
            }
        });
        btBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Profile.this, Booking.class);
                startActivity(intents);
            }
        });
        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Profile.this, Profile.class);
                startActivity(intents);
            }
        });
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearLoggedInUser(getBaseContext());
                Intent intents = new Intent(Profile.this, Login.class);
                startActivity(intents);
                finish();
            }
        });
    }
}
