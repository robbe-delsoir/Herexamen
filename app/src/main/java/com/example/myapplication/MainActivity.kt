package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit

const val Base_Url = "https://min-api.cryptocompare.com/"
class MainActivity : AppCompatActivity() {


    private lateinit var txtId: TextView
    private lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        okHttpClient = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

        getMyData()

        txtId = findViewById(R.id.txtId)


    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Base_Url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {

                val responseBody = response.body()
                val myStringBuilder = StringBuilder()
                if (responseBody != null) {
                    myStringBuilder.append(responseBody.BTC)
                }

                txtId.text = myStringBuilder

            }


            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.i("Error", "onFailure: "+t.message)

            }
        })
    }

}
