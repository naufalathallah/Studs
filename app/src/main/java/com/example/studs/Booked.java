package com.example.studs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Booked extends AppCompatActivity {
    private Button btHome, btRecord, btBooked, btProfile;
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.COLUMN_STUDIO_NAME, DatabaseHelper.COLUMN_TANGGAL, DatabaseHelper.COLUMN_JAM };

    final int[] to = new int[] { R.id.id, R.id.studio, R.id.tanggal, R.id.jam };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        /* Adapter untuk menunjuk data di database
        untuk di tampilkan dalam list  yang mana data tersebut
        menunjuk ke fragment dari ListView */
        adapter = new SimpleCursorAdapter(this, R.layout.activity_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        //pertombolan
        btHome = (Button) findViewById(R.id.bthome);
        btRecord = (Button) findViewById(R.id.btrecord);
        btBooked = (Button) findViewById(R.id.btbooked);
        btProfile = (Button) findViewById(R.id.btprofile);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booked.this, MainActivity.class);
                startActivity(intents);
            }
        });
        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booked.this, Record.class);
                startActivity(intents);
            }
        });
        btBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booked.this, Booking.class);
                startActivity(intents);
            }
        });
        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booked.this, Profile.class);
                startActivity(intents);
            }
        });


    }
}
