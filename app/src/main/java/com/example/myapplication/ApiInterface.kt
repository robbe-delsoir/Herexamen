package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("data/price?fsym=ETH&tsyms=BTC,USD,EUR")

    fun getData(): Call<MyData>
}