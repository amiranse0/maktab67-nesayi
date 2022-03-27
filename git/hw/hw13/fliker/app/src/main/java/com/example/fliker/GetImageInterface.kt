package com.example.quizretrofit

import androidx.lifecycle.LiveData
import com.example.fliker.ImageProperties
import com.github.leonardoxh.livedatacalladapter.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GetImageInterface {

    @GET("https://www.flickr.com/services/rest/")
    fun getImage(@QueryMap query:HashMap<String,String>):LiveData<Resource<ImageProperties>>
}