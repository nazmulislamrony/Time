package com.example.time.JsonDataCollect;

import com.example.time.Datas.MyDataJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    // it's my interface, it's api link: http://api.aladhan.com/v1/timingsByCity?city=Dhaka&country=Bangladesh
    //you can use post man of api, how can i use it, i already use the api
    //for future if you work on apis so use postman to view attribute and check your api if you have to create
    //when i create api i use postman for checking,
    // can i show , what i use?

    // is this same like postman and checker json?
    //yes but good from that you can pass post data also
    // oh great to know, i will use InShaAllah
    //and will save you work
    //you can do everything in postman
    // Thats great, i will use postman next time when i will work with api now how can i show the remaining time?
    //what do you mean by remaining time
    // let me sent you a screenshot in messenger
    //ok
    //api working data is getting
    // yes, api work nicely
    @GET("/timingsByCity")
    Call<MyDataJson> getPost(@Query("city") String city, @Query("country") String country);

}
