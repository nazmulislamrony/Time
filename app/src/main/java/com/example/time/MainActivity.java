package com.example.time;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.time.JsonDataCollect.RetrofitClass;

import retrofit2.Retrofit;


import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {



    TimeClass timeClass;

    // Stopwatch time

    TimerCount timerCount;
    RetrofitClass retrofit;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //where is the fajar ,zuhar ectc time
        //take variable for each prayer , Here? main activity?
        //in a place where you get the time

        //are retrofit is essential or you can use volley,
        // i'm just learning brother, that's it.
        // can i take sometimes? then i will knock you in messnger
        //I am search for retrofit then i will changes this,
        // thank you so much

        //do you work and i am searchhing retofit,
        // sure, why not, for retrofit, i use a interface,

        //you use kotlin? yes, let me show you, kotlin help to get serialize data from widget


        //just download post man have you use post man,
        // post man mean? don't understand brother






        timeClass=new TimeClass(this);

         timerCount=new TimerCount(this);


        retrofit=new RetrofitClass(this);

        TextView dummyText = findViewById(R.id.dummyText);


        //why it give us 10:00 the fajar is 04:01



    }



    void showRemaningTime(){

    }


}