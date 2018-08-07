package com.example.paxcel.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDBName.db";
    private static final String CONTACTS_TABLE_NAME ="contact_table";
    private static final String CONTACTS_COLUMN_ID = "id";
    private static final String CONTACTS_COLUMN_NAME = "name";
    private static final String CONTACTS_COLUMN_STREET = "street";
    private static final String CONTACTS_COLUMN_CITY = "city";
    private static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(" CREATE TABLE " + CONTACTS_TABLE_NAME + " (" +
                CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                CONTACTS_COLUMN_NAME + " TEXT NOT NULL, " +
                CONTACTS_COLUMN_PHONE + " TEXT NOT NULL, " +
                CONTACTS_COLUMN_STREET + " TEXT NOT NULL, " +
                CONTACTS_COLUMN_CITY + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+ CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(String name, String phone, String street, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_PHONE, phone);
        contentValues.put(CONTACTS_COLUMN_STREET, street);
        contentValues.put(CONTACTS_COLUMN_CITY, city);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+CONTACTS_TABLE_NAME+" where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String name, String phone, String street, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_PHONE, phone);
        contentValues.put(CONTACTS_COLUMN_STREET, street);
        contentValues.put(CONTACTS_COLUMN_CITY, city);
        db.update(CONTACTS_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CONTACTS_TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+CONTACTS_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}