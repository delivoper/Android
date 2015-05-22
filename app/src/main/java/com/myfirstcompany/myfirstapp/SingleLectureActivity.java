package com.myfirstcompany.myfirstapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SingleLectureActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_lecture);

        Intent intent = getIntent();

        String title = intent.getStringExtra(MainActivity.TAG_TITLE);
        String content = intent.getStringExtra(MainActivity.TAG_CONTENT);
        String pubdate = intent.getStringExtra(MainActivity.TAG_PUBLISH_DATE);
        String dlinedate = intent.getStringExtra(MainActivity.TAG_DEADLINE_DATE);

        TextView textTitle = ((TextView) findViewById(R.id.title));
        TextView textContent = ((TextView) findViewById(R.id.content));
        TextView textPubDate = ((TextView) findViewById(R.id.publishedDate));
        TextView textDlineDate = ((TextView) findViewById(R.id.deadlineDate));

        textTitle.setText(title);
        textContent.setText(content);
        textPubDate.setText(pubdate);
        textDlineDate.setText(dlinedate);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_lecture, menu);
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
