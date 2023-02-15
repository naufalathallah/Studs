package com.example.studs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudioDetail extends AppCompatActivity {
    private Button btHome, btRecord, btBooked, btProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_detail);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(StudioDetail.this, MainActivity.class);
                startActivity(intents);
            }
        });
        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(StudioDetail.this, Record.class);
                startActivity(intents);
            }
        });
        btBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(StudioDetail.this, Booked.class);
                startActivity(intents);
            }
        });
        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(StudioDetail.this, Profile.class);
                startActivity(intents);
            }
        });
    }
}
