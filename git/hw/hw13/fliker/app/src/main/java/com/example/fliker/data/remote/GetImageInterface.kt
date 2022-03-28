package com.example.fliker.data.remote

import androidx.lifecycle.LiveData
import com.example.fliker.data.model.ImageProperties
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GetImageInterface {

    @GET("https://www.flickr.com/services/rest/")
    fun getImage(@QueryMap query:HashMap<String,String>):LiveData<ApiResponse<ImageProperties>>
}