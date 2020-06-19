package com.example.time;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerCount {
    public Activity activity;

    TextView textViewCounter;
    Button buttonStartPause, buttonReset;

    private static final long START_TIME_IN_MILLIS = 5000000;
    private CountDownTimer countDownTimer;
    private boolean timmerRunning;
    private long timeLeftInMillis=START_TIME_IN_MILLIS;

    public TimerCount(MainActivity mainActivity){
        this.activity=mainActivity;

        buttonStartPause=mainActivity.findViewById(R.id.buttonPlay);
        buttonReset=mainActivity.findViewById(R.id.resetButton);
        textViewCounter=mainActivity.findViewById(R.id.textTime);


        buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timmerRunning){
                    pauseTimer();
                }else {
                    startTimer();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountdownText();

    }


    private void startTimer(){
        countDownTimer=new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                timmerRunning=false;
                buttonStartPause.setText("Start");
                buttonStartPause.setVisibility(View.INVISIBLE);
                buttonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        timmerRunning=true;
        buttonStartPause.setText("Pause");
        buttonReset.setVisibility(View.INVISIBLE);
    }


    private void pauseTimer(){
        countDownTimer.cancel();
        timmerRunning=false;
        buttonStartPause.setText("Start");
        buttonReset.setVisibility(View.VISIBLE);
    }


    private void resetTimer(){
        timeLeftInMillis=START_TIME_IN_MILLIS;
        updateCountdownText();
        buttonReset.setVisibility(View.INVISIBLE);
        buttonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountdownText(){

        long intMillis = timeLeftInMillis;
        long dd = TimeUnit.MILLISECONDS.toDays(intMillis);
        long daysMillis = TimeUnit.DAYS.toMillis(dd);
        intMillis -= daysMillis;
        long hh = TimeUnit.MILLISECONDS.toHours(intMillis);
        long hoursMillis = TimeUnit.HOURS.toMillis(hh);
        intMillis -= hoursMillis;
        long mm = TimeUnit.MILLISECONDS.toMinutes(intMillis);
        long minutesMillis = TimeUnit.MINUTES.toMillis(mm);
        intMillis -= minutesMillis;
        long ss = TimeUnit.MILLISECONDS.toSeconds(intMillis);
        long secondsMillis = TimeUnit.SECONDS.toMillis(ss);
        intMillis -= secondsMillis;

        String timeLeftFormatted= String.format(Locale.getDefault(),"%02d:%02d:%02d",hh, mm, ss);
        textViewCounter.setText(timeLeftFormatted);
    }
}
