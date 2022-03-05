package com.example.quizretrofit

import com.example.fliker.ImageProperties
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GetImageInterface {

    @GET("https://www.flickr.com/services/rest/")
    fun getImage(@QueryMap query:HashMap<String,String>):Call<ImageProperties>
}