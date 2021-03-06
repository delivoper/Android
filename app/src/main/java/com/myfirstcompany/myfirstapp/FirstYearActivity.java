package com.myfirstcompany.myfirstapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;


public class FirstYearActivity extends Activity {

    public static final int TAG_YEAR_CODE = 1;
    DBHelper db;

    CheckBox chkCom101, chkCom102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_year);

        db = new DBHelper(this);

        chkCom101 = (CheckBox)findViewById(R.id.chkBoxCom101);
        chkCom102 = (CheckBox)findViewById(R.id.chkBoxCom102);

        setUIFirst(db.getAllCodes(TAG_YEAR_CODE));
    }

    public void setUIFirst(ArrayList codeArray){
        Log.d("SET UI FIRST :", " ArrayList: " + codeArray);

/*        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0));
        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0).getClass());*/

        for(int i=0; i<codeArray.size(); i++) {
/*            Log.d("codeArray.get(): ", ">" + codeArray.get(i));
            Log.d("checkText: ", ">" + chkCom101.getText().toString());*/

            if( codeArray.get(i).equals(chkCom101.getText().toString()) ){
                chkCom101.setChecked(true);
                Log.d("equal","equal" + codeArray.get(i));
            }
            else if( codeArray.get(i).equals(chkCom102.getText().toString()) ){
                chkCom102.setChecked(true);
                Log.d("equal", "equal" + codeArray.get(i));
            }
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_year, menu);
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
