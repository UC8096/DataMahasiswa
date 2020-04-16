package com.example.datamahasiswa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String MHS_TABLE_NAME = "mahasiswa";
    public static final String MHS_COLUMN_ID = "id";
    public static final String MHS_COLUMN_NAMA = "nama";
    public static final String MHS_COLUMN_PHONE = "phone";
    public static final String MHS_COLUMN_EMAIL = "email";
    public static final String MHS_COLUMN_ADDRESS = "address";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table mahasiswa " +
                        "(id integer primary key, nim text,nama text,phone text,email text,address text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }

    public boolean insertContact(String nama, String phone, String email, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("address", address);

        db.insert("mahasiswa", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mahasiswa where id=" + id + "", null);
        return res;
    }

    public boolean deleteContact(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("mahasiswa", MHS_COLUMN_ID + "=" + nim, null);
        return true;
    }

    public boolean updateContact(String id, String nama, String phone, String email, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("address", address);

        db.update("mahasiswa", contentValues, MHS_COLUMN_ID + "=" + id, null);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                MHS_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mahasiswa", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(MHS_COLUMN_NAMA)));
            res.moveToNext();
        }
        return array_list;
    }
}
