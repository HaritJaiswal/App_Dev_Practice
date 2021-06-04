package com.example.databasetrialapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.databasetrialapp.MyDbHelper;

public class MyDbAdapter {
    MyDbHelper myhelper;

    public MyDbAdapter(Context context) {
        myhelper = new MyDbHelper(context);
    }

    public long insertData(String name, String pass) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.EMAIL, name);
        contentValues.put(MyDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(MyDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {MyDbHelper.UID, MyDbHelper.EMAIL, MyDbHelper.MyPASSWORD};
        Cursor cursor =db.query(MyDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();

        while (cursor.moveToNext()) {
            int cid =cursor.getInt(cursor.getColumnIndex(MyDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(MyDbHelper.EMAIL));
            String password =cursor.getString(cursor.getColumnIndex(MyDbHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        cursor.close();
        return buffer.toString();
    }

    public  int delete(String uname) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(MyDbHelper.TABLE_NAME ,MyDbHelper.EMAIL+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.EMAIL,newName);
        String[] whereArgs= {oldName};
        int count =db.update(MyDbHelper.TABLE_NAME,contentValues, MyDbHelper.EMAIL+" = ?",whereArgs );
        return count;
    }

    protected void destroyDB() {
        myhelper.close();
    }
}
