package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val Base_Url = "https://min-api.cryptocompare.com/"
class MainActivity : AppCompatActivity() {

    private lateinit var txtBTC: TextView
    private lateinit var txtEUR: TextView
    private lateinit var txtUSD: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMyData();

        val secondActButton = findViewById<Button>(R.id.buttonAbout)
        secondActButton.setOnClickListener{
            val intent = Intent(this, activity_about::class.java)
            startActivity(intent)
        }

        txtUSD = findViewById(R.id.txtUSD)
        txtEUR = findViewById(R.id.txtEUR)
        txtBTC = findViewById(R.id.txtBTC)


    }


    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_Url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {

                val responseBody = response.body()
                val responseBodyTwo = response.body()
                val responseBodyThree = response.body()

                val myStringBuilder = StringBuilder()
                if (responseBody != null) {
                    myStringBuilder.append(responseBody.BTC)

                }

                val myStringBuilder2 = StringBuilder()
                if (responseBody != null) {
                    if (responseBodyTwo != null) {
                        myStringBuilder.append(responseBodyTwo.EUR)
                    }
                }

                val myStringBuilder3 = StringBuilder()
                if (responseBody != null) {
                    if (responseBodyThree != null) {
                        myStringBuilder.append(responseBodyThree.USD)
                    }
                }

                txtBTC.text = myStringBuilder
                txtEUR.text = myStringBuilder2
                txtUSD.text = myStringBuilder3

            }


            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)

            }
        })
    }

}