package com.example.netflix.postimage

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ProfileImageService {
    @Multipart
    @POST("http://51.195.19.222/users/{userName}")
    fun sendProfileImage(@Path("userName") userName:String,
        @Part image: MultipartBody.Part): Call<Any>

    @GET("http://51.195.19.222/uploads/{name}")
    fun getProfileImage(@Path("name") name:String): Call<String>

}