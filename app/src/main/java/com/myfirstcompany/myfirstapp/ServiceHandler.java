package com.myfirstcompany.myfirstapp;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by trang on 21.05.2015.
 */
public class ServiceHandler {

/*    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;*/

    public ServiceHandler(){

    }

/*    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }

    public String makeServiceCall(String url, int method, List<NameValuePair> params) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            if (method == POST) {
                HttpPost httpPost = new HttpPost(url);
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);
            } else if (method == GET) {
                if (params != null) {
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    Log.d("ParamString: ", "> " + paramString);
                    url = url + "?" + paramString;
                    //url = url + "?" + "lecture=" + "com240";
                }
                HttpGet httpGet = new HttpGet(url);

                httpResponse = httpClient.execute(httpGet);
            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

        }   catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   catch (ClientProtocolException e) {
            e.printStackTrace();
        }   catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }*/

    InputStream is = null;
    BufferedReader reader = null;
    static String webPage = "";

    public String downloadUrl(String my_url) {
        return this.downloadUrl(my_url, null);
    }

    public String downloadUrl(String my_url, List<NameValuePair> params) {
        try {
            if( params != null ){
                String paramString = URLEncodedUtils.format(params, "utf-8");
                my_url += "?" + paramString;
                Log.d("The paramString is: ", ">" + paramString);
            }

            URL url = new URL(my_url);

            Log.d("The URL is: ",">" + url);
//            Log.d("Type of the URL is: ",">" + url.getClass().getName());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();

/*            int response = conn.getResponseCode();
            Log.d("The response is : ",">" + response);*/
            is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
/*            Log.d("The inputStream is: ",">" + is);
            Log.d("The Reader is: ",">" + reader);*/
            String data = null;
            while((data = reader.readLine()) != null) {
                webPage += data + "\n";
            }
            return webPage;
        } catch(IOException e) {
            return new String("Exception: " + e.getMessage());
        }

    }

}

