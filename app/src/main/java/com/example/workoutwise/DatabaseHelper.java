package com.example.workoutwise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "userinfo.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "userinfo_table";

    public static final String COL_NAME = "NAME";
    public static final String COL_SURNAME = "SURNAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_PASSWORD = "Password";
    public Cursor getDataByPhone(String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_PHONE + " LIKE '%" + phone + "%'", null);
        return res;
    }
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //  SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_PASSWORD + " TEXT, " + COL_NAME + " TEXT, " + COL_SURNAME + " TEXT, " + COL_EMAIL + " TEXT, " + COL_PHONE + " TEXT);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String password, String name, String surname, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SURNAME, surname);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String password, String name, String surname, String email, String phone ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SURNAME, surname);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_PASSWORD, password);
        db.update(TABLE_NAME, contentValues, "PASSWORD = ?", new String[] {password});
        return true;
    }
    public Integer deleteData (String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "PASSWORD = ?", new String[] {password});
    }

//    private boolean dataExists(SQLiteDatabase db, String id) {
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?", new String[]{id});
//        boolean exists = (cursor.getCount() > 0);
//        cursor.close();
//        return exists;
//    }

    public boolean dataExists(String id, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_PASSWORD + " = ? AND " + COL_EMAIL + " = ?", new String[]{id, email});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
