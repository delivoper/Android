package com.myfirstcompany.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by trang on 22.05.2015.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final Integer DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "lectureCodes.db";
    public static final String LECTURES_TABLE_NAME = "lectures";
    public static final String LECTURES_COLUMN_ID = "id";
    public static final String LECTURES_COLUMN_CODE = "code";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table lectures" + "(id integer primary key, code text");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS lectures");
        onCreate(db);
    }

    public void insertLecture(String code) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("code", code);
        mydb.insert(LECTURES_TABLE_NAME, null, contentValues);
    }

    public void deleteLecture(String code) {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete("lectures", "code = ?", new String[]{code});
    }

    public ArrayList getAllCodes(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from lectures", null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex(LECTURES_COLUMN_CODE)));
            res.moveToNext();
        }
        return arrayList;
    }
}
