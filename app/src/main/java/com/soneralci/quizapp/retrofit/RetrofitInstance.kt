package com.soneralci.quizapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    val baseUrl = "http://192.168.1.5/quiz/"

    fun getRetrofitInstance() : Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}