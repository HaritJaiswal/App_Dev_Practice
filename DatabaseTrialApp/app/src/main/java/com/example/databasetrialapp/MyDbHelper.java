package com.example.databasetrialapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "myDatabase";    // Database Name
    static final String TABLE_NAME = "myTable";   // Table Name
    static final int DATABASE_Version = 1;    // Database Version
    static final String UID="_id";     // Column I (Primary Key)
    static final String EMAIL = "Email";    //Column II
    static final String MyPASSWORD= "Password";    // Column III
    static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EMAIL+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225));";
    static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            ToastMessage.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            ToastMessage.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            ToastMessage.message(context,""+e);
        }
    }
}