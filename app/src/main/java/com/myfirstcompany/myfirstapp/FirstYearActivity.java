package com.myfirstcompany.myfirstapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;


public class FirstYearActivity extends Activity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_year);

        db = new DBHelper(this);
    }

    public void onCheckBoxClicked(View view){

        boolean checked = ((CheckBox) view).isChecked();
        String lectureCode = ((CheckBox) view).getText().toString();

        if(checked){
            // add to database
            Log.d("ADD: ", ">" + lectureCode);
//            db.insertLecture(lectureCode);
        } else{
            // remove from database
            Log.d("REMOVE: ", ">" + lectureCode);
//            db.deleteLecture(lectureCode);
        }

        //Log.d("ALL RECORDS: ",">" + db.getAllCodes());
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
