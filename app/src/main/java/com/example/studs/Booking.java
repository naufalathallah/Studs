package com.example.studs;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.HashMap;

public class Booking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btHome, btRecord, btBooked, btProfile, btHistory;
    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    public String sTanggal;
    DatabaseHelper dbHelper;
    private DBManager dbManager;
    Calendar newCalendar = Calendar.getInstance();
    private SQLiteDatabase database;
    private Spinner spinner,spinner2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        // Membuat objek dari kelas DBManager
        dbManager = new DBManager(this);
        dbManager.open();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jam, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.jam);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.studio, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2 = findViewById(R.id.studio);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        //activity_booking xml
        Button btnBook = findViewById(R.id.btBooking);
//        EditText etStudio = findViewById(R.id.studio);
        etTanggal = findViewById(R.id.tgl);
        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.requestFocus();
//        Spinner etJam = findViewById(R.id.jam);
        setDateTimeField();

        //pertombolan
        btHome = (Button) findViewById(R.id.bthome);
        btRecord = (Button) findViewById(R.id.btrecord);
        btBooked = (Button) findViewById(R.id.btbooked);
        btProfile = (Button) findViewById(R.id.btprofile);
        btHistory = (Button) findViewById(R.id.btHistory);


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String studio = spinner2.getSelectedItem().toString();
                final String tanggal = etTanggal.getText().toString();
                final String jam = spinner.getSelectedItem().toString();
//                Cursor cursor = dbManager.validasi_book();
                //error
//                if (cursor == etTanggal) {
//                    Toast.makeText(getBaseContext(), "Full", Toast.LENGTH_LONG).show();
//                }

                if (studio.isEmpty() || tanggal.isEmpty() || jam.isEmpty()){
                    Toast.makeText(getBaseContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
                } else {
                    validasi_waktu(studio,tanggal,jam);
                    Log.d("tanggal ",tanggal);
                    Log.d("jam ",jam);
                    /* Memanggil fungsi insert melalui objek dbManager */
//                    dbManager.addBooking(studio, tanggal, jam);
//                    Toast.makeText(getBaseContext(), "Booking Berhasil", Toast.LENGTH_LONG).show();
//                    Intent intents = new Intent(Booking.this, Booking.class);
//                    startActivity(intents);
                }
            }
        });


        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booking.this, MainActivity.class);
                startActivity(intents);
            }
        });
        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booking.this, Record.class);
                startActivity(intents);
            }
        });
        btBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booking.this, Booking.class);
                startActivity(intents);
            }
        });
        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booking.this, Profile.class);
                startActivity(intents);
            }
        });

        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Booking.this, Booked.class);
                startActivity(intents);
            }
        });

    }
    public Cursor validasi_waktu(String studio, String tanggal, String jam) {
        String where = DatabaseHelper.COLUMN_TANGGAL + " ='"+tanggal+"' AND "+DatabaseHelper.COLUMN_JAM + " ='"+jam+"'";
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.COLUMN_STUDIO_NAME, DatabaseHelper.COLUMN_TANGGAL, DatabaseHelper.COLUMN_JAM };
        Cursor cursor = database.query(DatabaseHelper.TABLE_BOOKING, columns, where, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        int jumlah = cursor.getCount();
        if (jumlah == 0){
            /* Memanggil fungsi insert melalui objek dbManager */
                    dbManager.addBooking(studio, tanggal, jam);
                    Toast.makeText(getBaseContext(), "Booking Berhasil", Toast.LENGTH_LONG).show();
                    Intent intents = new Intent(Booking.this, Booking.class);
                    startActivity(intents);
        }
        else{
            Toast.makeText(getBaseContext(), "tanggal sudah do booking", Toast.LENGTH_LONG).show();
        }

        Log.d("validasi",String.valueOf(jumlah));
        return cursor;
    }


    private void setDateTimeField() {
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal.show();
            }
        });

        dpTanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                sTanggal = dayOfMonth + " " + bulan[monthOfYear] + " " + year;
                etTanggal.setText(sTanggal);



            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String pilihan = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), pilihan, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
