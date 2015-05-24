package com.myfirstcompany.myfirstapp;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by trang on 23.05.2015.
 */
public class DateHandler {

    private Date date;
    String expectedPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public DateHandler() {

    }

    public Date setDateObject(String myDateString) {
        SimpleDateFormat df = new SimpleDateFormat(expectedPattern, Locale.UK);
        try {
            date = df.parse(myDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getDateObject() {
        return date;
    }

    public String getClock(Date myDate) {
        SimpleDateFormat clockFormat = new SimpleDateFormat("HH:mm");
        String clockString = clockFormat.format(myDate);

        return clockString;
    }

    public String getDate(Date myDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String dateString = dateFormat.format(myDate);

        return dateString;
    }

}
