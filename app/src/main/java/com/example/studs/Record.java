package com.example.studs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Record extends AppCompatActivity {
    private Button btHome, btRecord, btBooked, btProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //pertombolan
        btHome = (Button) findViewById(R.id.bthome);
        btRecord = (Button) findViewById(R.id.btrecord);
        btBooked = (Button) findViewById(R.id.btbooked);
        btProfile = (Button) findViewById(R.id.btprofile);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Record.this, MainActivity.class);
                startActivity(intents);
            }
        });
        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Record.this, Record.class);
                startActivity(intents);
            }
        });
        btBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Record.this, Booking.class);
                startActivity(intents);
            }
        });
        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Record.this, Profile.class);
                startActivity(intents);
            }
        });
    }
}
