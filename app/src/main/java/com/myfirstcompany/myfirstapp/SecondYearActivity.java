package com.myfirstcompany.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;


public class SecondYearActivity extends Activity {

    public static final int TAG_YEAR_CODE = 2;
    DBHelper db;

    private CheckBox chkCom240, chkCom252;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_year);

        db = new DBHelper(this);

        chkCom240 = (CheckBox)findViewById(R.id.chkBoxCom240);
        chkCom252 = (CheckBox)findViewById(R.id.chkBoxCom252);

        setUIFirst(db.getAllCodes(TAG_YEAR_CODE));
    }

    public void deleteAllRecords(View view) {
        db.onDeleteAll();
        setUIFirst(db.getAllCodes());
    }

    public void onCheckBoxClicked(View view){

        boolean checked = ((CheckBox) view).isChecked();
        String lectureCode = ((CheckBox) view).getText().toString();

        //Log.d("DENEME: ",">" + view.getContext().getClass().getSimpleName());

        if(checked){
            // add to database
            Log.d("ADD: ", ">" + lectureCode);
            db.insertLecture(lectureCode, TAG_YEAR_CODE);
        } else{
            // remove from database
            Log.d("REMOVE: ", ">" + lectureCode);
            db.removeLecture(lectureCode, TAG_YEAR_CODE);
        }

        Log.d("ALL RECORDS WITH YEAR: ", ">" + db.getAllCodes(TAG_YEAR_CODE));
        Log.d("ALL RECORDS: ", ">" + db.getAllCodes());
    }

    public void setUIFirst(ArrayList codeArray){
        Log.d("SET UI FIRST :", " ArrayList: " + codeArray);

/*        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0));
        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0).getClass());*/

        if(codeArray.size() == 0 ){
            chkCom240.setChecked(false);
            chkCom252.setChecked(false);
        } else {
            for (int i = 0; i < codeArray.size(); i++) {
    /*            Log.d("codeArray.get(): ", ">" + codeArray.get(i));
                Log.d("checkText: ", ">" + chkCom101.getText().toString());*/

                if (codeArray.get(i).equals(chkCom240.getText().toString())) {
                    chkCom240.setChecked(true);
                } else if (codeArray.get(i).equals(chkCom252.getText().toString())) {
                    chkCom252.setChecked(true);
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_year, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
