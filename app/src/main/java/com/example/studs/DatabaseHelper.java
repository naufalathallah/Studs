package com.example.studs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nama Tabel
    public static final String TABLE_USER = "tbl_user";
    public static final String TABLE_BOOKING = "tbl_booking";

    // User Table Columns names
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_USER_POINT = "user_point";

    // Booking Table Columns names
    public static final String _ID = "_id";
    public static final String COLUMN_STUDIO_NAME = "studio_name";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_JAM = "jam";

    // Nama Database
    static final String DB_NAME = "studs.db";

    // Versi Database
    static final int DB_VERSION = 1;

    // create user table sql query
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_POINT + " INTEGER " + ")";

    // create booking table sql query
    private static final String CREATE_TABLE_BOOKING = "CREATE TABLE " + TABLE_BOOKING + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_STUDIO_NAME + " TEXT,"
            + COLUMN_TANGGAL + " TEXT," + COLUMN_JAM + " TEXT " + ")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BOOKING);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKING);
        onCreate(db);
    }
}
