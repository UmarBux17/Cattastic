package com.cat.cattasticapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    //site the info is coming from
    // https://get-meme/gimme
    @GET("/v1/breeds")
    fun getData() : Call<responseDataClass>
}