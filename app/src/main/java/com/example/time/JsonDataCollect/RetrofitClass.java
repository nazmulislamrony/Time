package com.example.time.JsonDataCollect;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.time.Datas.MyDataJson;
import com.example.time.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.time.R;
import com.example.time.TimerCount;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class RetrofitClass {
    TextView prayerTime;
    TextView dummyText;
    JsonPlaceHolderApi jsonPlaceHolderApi;


    String fajar = "";
    String Asr = "";
    String Duhar = "";
    String maghrib = "";
    String Esha = "";


    public Activity activity;

    public RetrofitClass(MainActivity mainActivity){
        this.activity=mainActivity;

        prayerTime=mainActivity.findViewById(R.id.prayerTime);
        dummyText=mainActivity.findViewById(R.id.dummyText);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://api.aladhan.com/v1/")
//                .baseUrl("https://simplifiedcoding.net/demos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);

        dataMethod();
    }

    private void dataMethod() {
        String value="Dhaka";
        String count="Bangladesh";
        Call <MyDataJson> call=jsonPlaceHolderApi.getPost(value,count);
        call.enqueue(new Callback<MyDataJson>() {
            @Override
            public void onResponse(Call<MyDataJson> call, Response<MyDataJson> response) {
                if (!response.isSuccessful()) {

                    prayerTime.setText(response.code());

                    Log.d("checkMessage", "responsenotsucces: " + response.code());
                    return;
                }
                MyDataJson myDatajson = response.body();


                String content = "";

                content += "Fajar: " + myDatajson.getData().getTimings().getFajr() + "\n";
                content += "Asr: " + myDatajson.getData().getTimings().getAsr() + "\n";
                content += "Duhur: " + myDatajson.getData().getTimings().getDhuhr() + "\n";
                content += "Maghrib: " + myDatajson.getData().getTimings().getMaghrib() + "\n";
                content += "Esha: " + myDatajson.getData().getTimings().getIsha();

                fajar = myDatajson.getData().getTimings().getFajr();
                Asr = myDatajson.getData().getTimings().getAsr();
                Duhar = myDatajson.getData().getTimings().getDhuhr();
                maghrib = myDatajson.getData().getTimings().getMaghrib();
                Esha = myDatajson.getData().getTimings().getIsha();

                String timeStamp = myDatajson.getData().getDate().getTimestamp();

                Log.d("fajarcheck", "fajar: " + fajar);


                Log.d("checkMessage", "responseprayertime: " + content + ": " + myDatajson.getData().getTimings().getIsha());

                prayerTime.append(content);

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                try {

                    String time = Esha;

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

                    String currentDateandTime = sdf2.format(new Date()); // to get direct time

                    Date date = sdf.parse(time);

                    Date curreenttime = sdf2.parse(currentDateandTime);

//                  long difference = date.getTime() - curreenttime.getTime();
                    long difference = date.getTime() - Long.parseLong(timeStamp);

                    Log.d("difference","checkdiff: "+difference);

                     if(difference<=0){
                        time = Duhar; // oh understand
                        difference = date.getTime() - curreenttime.getTime();
                    }else if(difference<=0){
                        time = Asr;
                        difference = date.getTime() - curreenttime.getTime();
                    }else if(difference<=0){
                        time = maghrib;
                        difference = date.getTime() - curreenttime.getTime();
                    }else if(difference <= 0){
                         time = Esha;
                         difference = date.getTime() - curreenttime.getTime();
                     }


                    long finalDifference = difference;
                    CountDownTimer c = new CountDownTimer(finalDifference+1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            long intMillis = millisUntilFinished;

                            long diffMin=intMillis / (60 * 1000) % 60;
                            long diffHours = intMillis / (60 * 60 * 1000);
                            long diffSeconds = intMillis / 1000 % 60;
                            dummyText.setText("-"+diffHours + ":" + diffMin+":"+diffSeconds);
                        }

                        public void onFinish() {

                        }
                    };

                    c.start();




//







                } catch (ParseException e) {
                    Log.d("difference","error: "+e.toString());
                }
            }
            @Override
            public void onFailure(Call<MyDataJson> call, Throwable t) {
                prayerTime.setText(t.getMessage());

                Log.d("checkMessage","failure message: " + t.getMessage());

            }
        });
    }

    public String getFajar() {
        return fajar;
    }

    public String getAsr() {
        return Asr;
    }

    public String getDuhar() {
        return Duhar;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getEsha() {
        return Esha;
    }
}
