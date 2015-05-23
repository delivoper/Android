package com.myfirstcompany.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by trang on 22.05.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lectureCodes";

    public static final Integer DATABASE_VERSION = 7;

    public static final String TABLE_NAME = "lectures";
    public static final String COLUMN_ID = "id";

    public static final String COLUMN_CODE_1 = "firstYear";
    public static final String COLUMN_CODE_2 = "secondYear";
    public static final String COLUMN_CODE_3 = "thirdYear";
    public static final String COLUMN_CODE_4 = "fourthYear";

    private String FINAL_COLUMN_CODE = null;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CODE_1 + " TEXT, "
                + COLUMN_CODE_2 + " TEXT, "
                + COLUMN_CODE_3 + " TEXT, "
                + COLUMN_CODE_4 + " TEXT " + ");";
        //String CREATE_TABLE = "CREATE TABLE lectures ( code TEXT )";
        db.execSQL(CREATE_TABLE);
/*        db.execSQL("CREATE TABLE lectures " +
                "(code TEXT)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS lectures");
        onCreate(db);
    }

    public void onDeleteAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME );
        db.close();
    }

    public void insertLecture(String code, int yearColumn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("YEAR COLUMN: ", ">" + yearColumn);
        switch(yearColumn){
            case 1:
                FINAL_COLUMN_CODE = COLUMN_CODE_1;
                break;
            case 2:
                FINAL_COLUMN_CODE = COLUMN_CODE_2;
                break;
            case 3:
                FINAL_COLUMN_CODE = COLUMN_CODE_3;
                break;
            case 4:
                FINAL_COLUMN_CODE = COLUMN_CODE_4;
                break;
        }
        //Log.d("CODE: ", ">" + code);
        Log.d("FINAL COLUMN CODE: ", ">" + FINAL_COLUMN_CODE);
        contentValues.put(FINAL_COLUMN_CODE, code);
        Log.d("CONTENT VALUES: ", ">" + contentValues);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void removeLecture(String code, int yearColumn) {
        Log.d("YEAR COLUMN: ", ">" + yearColumn);
        switch(yearColumn){
            case 1:
                FINAL_COLUMN_CODE = COLUMN_CODE_1;
                break;
            case 2:
                FINAL_COLUMN_CODE = COLUMN_CODE_2;
                break;
            case 3:
                FINAL_COLUMN_CODE = COLUMN_CODE_3;
                break;
            case 4:
                FINAL_COLUMN_CODE = COLUMN_CODE_4;
                break;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, FINAL_COLUMN_CODE + " = ?", new String[]{code});
        db.close();
    }

    // For all records
    public ArrayList getAllCodes(){

        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " + COLUMN_CODE_1 + " , "
                    + COLUMN_CODE_2 + " , " + COLUMN_CODE_3
                    + " , " + COLUMN_CODE_4 + " FROM " + TABLE_NAME + " GROUP BY "
                    + COLUMN_CODE_1 + " , " + COLUMN_CODE_2 + " , " + COLUMN_CODE_3 + " , "
                    + COLUMN_CODE_4;

        Cursor res = db.rawQuery(selectQuery, null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex(COLUMN_CODE_1)));
            arrayList.add(res.getString(res.getColumnIndex(COLUMN_CODE_2)));
            arrayList.add(res.getString(res.getColumnIndex(COLUMN_CODE_3)));
            arrayList.add(res.getString(res.getColumnIndex(COLUMN_CODE_4)));
            res.moveToNext();
        }
        db.close();
        return arrayList;
    }

    // Records according to years
    public ArrayList getAllCodes(int yearColumn){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        switch(yearColumn){
            case 1:
                FINAL_COLUMN_CODE = COLUMN_CODE_1;
                break;
            case 2:
                FINAL_COLUMN_CODE = COLUMN_CODE_2;
                break;
            case 3:
                FINAL_COLUMN_CODE = COLUMN_CODE_3;
                break;
            case 4:
                FINAL_COLUMN_CODE = COLUMN_CODE_4;
                break;
        }

        String selectQuery = "SELECT DISTINCT " + FINAL_COLUMN_CODE + " FROM " + TABLE_NAME;
        //String selectQuery = "SELECT * FROM " + TABLE_NAME;
        //String selectQuery = "SELECT CODE FROM " + TABLE_NAME;
        Cursor res = db.rawQuery(selectQuery, null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            String record = res.getString(res.getColumnIndex(FINAL_COLUMN_CODE));
            if(record != null) {
                arrayList.add(record);
            }
            res.moveToNext();
        }
        db.close();

        return arrayList;
    }
}
