package com.example.studs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Button btHome, btRecord, btBooked, btProfile;
    private ImageView s1,s2,s3;
    private String itemname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.lokasi);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lokasi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //pertombolan
        btHome = (Button) findViewById(R.id.bthome);
        btRecord = (Button) findViewById(R.id.btrecord);
        btBooked = (Button) findViewById(R.id.btbooked);
        btProfile = (Button) findViewById(R.id.btprofile);

        s1 = (ImageView) findViewById(R.id.s1);
        s2 = (ImageView) findViewById(R.id.s2);
        s3 = (ImageView) findViewById(R.id.s3);


        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intents);
            }
        });
        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(MainActivity.this, Record.class);
                startActivity(intents);
            }
        });
        btBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(MainActivity.this, Booking.class);
                startActivity(intents);
            }
        });
        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(MainActivity.this, Profile.class);
                startActivity(intents);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String pilihan = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), pilihan, Toast.LENGTH_SHORT).show();
        gantigambar();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void gantigambar() {
        itemname = spinner.getSelectedItem().toString();
        if (itemname.equals("Depok")) {
            s1.setImageResource(R.drawable.std4);
            s2.setImageResource(R.drawable.std5);
            s3.setImageResource(R.drawable.std6);
        }else if(itemname.equals("Jakarta")){
            s1.setImageResource(R.drawable.std7);
            s2.setImageResource(R.drawable.std8);
            s3.setImageResource(R.drawable.std9);
        }else{
            s1.setImageResource(R.drawable.std1);
            s2.setImageResource(R.drawable.std2);
            s3.setImageResource(R.drawable.std3);
        }
    }
}