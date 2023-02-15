package com.example.studs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){
        context = c;
    }
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Query add user
    public void addUser(String uname, String pw, String poin) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_USER_NAME, uname);
        contentValue.put(DatabaseHelper.COLUMN_USER_PASSWORD, pw);
        contentValue.put(DatabaseHelper.COLUMN_USER_POINT, poin);
        database.insert(DatabaseHelper.TABLE_USER, null, contentValue);
    }

    //add booking
    public void addBooking(String studio, String tanggal, String jam) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_STUDIO_NAME, studio);
        contentValue.put(DatabaseHelper.COLUMN_TANGGAL, tanggal);
        contentValue.put(DatabaseHelper.COLUMN_JAM, jam);
        database.insert(DatabaseHelper.TABLE_BOOKING, null, contentValue);
    }

    // Query ambil/read data
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.COLUMN_STUDIO_NAME, DatabaseHelper.COLUMN_TANGGAL, DatabaseHelper.COLUMN_JAM };
        Cursor cursor = database.query(DatabaseHelper.TABLE_BOOKING, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor ambiluser() {
        String[] columns = new String[] { DatabaseHelper.COLUMN_USER_ID, DatabaseHelper.COLUMN_USER_NAME};
        Cursor cursor = this.database.query(DatabaseHelper.TABLE_USER, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // user verify
    public boolean checkUser(String uname, String password) {

        // array of columns to fetch
        String[] columns = {
                DatabaseHelper.COLUMN_USER_ID
        };
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // selection criteria
        String selection = DatabaseHelper.COLUMN_USER_NAME + " = ?" + " AND " + DatabaseHelper.COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {uname, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
