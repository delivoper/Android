package com.myfirstcompany.myfirstapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ListActivity {

    public static final String TAG_TITLE = "title";
    public static final String TAG_CONTENT = "content";
    public static final String TAG_PUBLISH_DATE = "created_at";
    public static final String TAG_DEADLINE_DATE = "deadline";

    private static final String TAG_LECTURE_ID = "lecture_id";
    private static final String TAG_POST_ID = "id";
    private static final String TAG_UPDATE = "updated_at";
    private static final String TAG_USER_ID = "user_id";

    private ProgressDialog pDialog;

    private static String url = "http://188.166.44.115/api/show";

    JSONArray announcements = null;

    ArrayList<HashMap<String, String>> contactList;

    List<NameValuePair> params = new ArrayList<NameValuePair>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        params.add(new BasicNameValuePair("lecture", "com252"));

        contactList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String code = ((TextView) view.findViewById(R.id.lectureCode))
//                        .getText().toString();
                String title = ((TextView) view.findViewById(R.id.announcementTitle))
                        .getText().toString();
                String content = ((TextView) view.findViewById(R.id.announcementContent))
                        .getText().toString();
                String pubdate = ((TextView) view.findViewById(R.id.publishedDate))
                        .getText().toString();
                String dlinedate = ((TextView) view.findViewById(R.id.deadlineDate))
                        .getText().toString();

                Intent in = new Intent(getApplicationContext(), SingleLectureActivity.class);
//                in.putExtra(TAG_CODE, code);
                in.putExtra(TAG_TITLE, title);
                in.putExtra(TAG_CONTENT, content);
                in.putExtra(TAG_PUBLISH_DATE, pubdate);
                in.putExtra(TAG_DEADLINE_DATE, dlinedate);
                startActivity(in);
            }
        });
        new GetContacts().execute();
    }

//    public void getData(View view) {
//        new GetContacts().execute();
//    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_http_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void getData(View view){
        new DownloadWebpageTask(this, textView).execute(link);
    }*/

/*    public void getData(View view){
        new GetContacts(this, textView).execute(link);
    }*/

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET, params);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONArray jsonArr = new JSONArray(jsonStr);
                    announcements = new JSONArray(jsonStr);
                    // Getting JSON Array node
                    //announcements = jsonArr.getJSONObject();

                    // looping through All Contacts
                    for (int i = 0; i < announcements.length(); i++) {
                        JSONObject c = announcements.getJSONObject(i);

                        String id = c.getString(TAG_POST_ID);
                        String title = c.getString(TAG_TITLE);
                        String content = c.getString(TAG_CONTENT);
                        String deadline = c.getString(TAG_DEADLINE_DATE);
                        String pubdate = c.getString(TAG_PUBLISH_DATE);
                        String userid = c.getString(TAG_USER_ID);
                        String lectureid = c.getString(TAG_LECTURE_ID);


                        // tmp hashmap for single contact
                        HashMap<String, String> announcement = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        announcement.put(TAG_POST_ID, id);
                        announcement.put(TAG_TITLE, title);
                        announcement.put(TAG_CONTENT, content);
                        announcement.put(TAG_DEADLINE_DATE, deadline);
                        announcement.put(TAG_PUBLISH_DATE, pubdate);
                        announcement.put(TAG_USER_ID, userid);
                        announcement.put(TAG_LECTURE_ID, lectureid);

                        // adding contact to contact list
                        contactList.add(announcement);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[] { TAG_TITLE,
                    TAG_CONTENT, TAG_PUBLISH_DATE, TAG_DEADLINE_DATE },
                    new int[] {
                    R.id.announcementTitle, R.id.announcementContent,
                    R.id.publishedDate, R.id.deadlineDate });

            setListAdapter(adapter);
        }

    }


}
