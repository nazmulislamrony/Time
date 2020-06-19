package com.example.time.Datas


import com.google.gson.annotations.SerializedName

data class MyDataJson(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)