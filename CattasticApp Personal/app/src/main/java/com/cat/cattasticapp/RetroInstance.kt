package com.cat.cattasticapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.thecatapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //object needs to pull the interface
    val someInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}