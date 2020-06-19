package com.example.time;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

public class TimeClass{

    public Activity activity;


    TextView dateText, timeText;
    Context mContext;

    public TimeClass(MainActivity mainActivity) {

        this.activity=mainActivity;

        dateText=(TextView)this.activity.findViewById(R.id.dateText);
        timeText=(TextView) this.activity.findViewById(R.id.secondText);

//        Calendar cal = Calendar.getInstance();

//        int year = cal.get(java.util.Calendar.YEAR); // get the current year
//        int month = cal.get(java.util.Calendar.MONTH); // month...
//        int day = cal.get(Calendar.DAY_OF_MONTH); // current day in the month
//
//        int hour=cal.get(java.util.Calendar.HOUR); // get the hour
//        int miniute=cal.get(java.util.Calendar.MINUTE); // get the minute
//        int second=cal.get(java.util.Calendar.SECOND); // get the second

//        Log.d("timetext","hourminsec: "+hour +miniute+second);



        final android.os.Handler someHandler=new Handler();

        someHandler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {



                //Display the time
                timeText.setText(new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH).format(new Date()));
                someHandler.postDelayed(this, 1000);

                Date date = Calendar.getInstance().getTime();

                // Display a date in day, month, year format


                DateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
                String today = formatter.format(date);
                dateText.setText(today);
            }
        }, 10);


// i use it to get the time and date



    }





}
