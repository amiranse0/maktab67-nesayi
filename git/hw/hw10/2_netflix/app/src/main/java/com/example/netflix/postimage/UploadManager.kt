package com.example.netflix.postimage

import com.example.netflix.getmoviewithretrofit.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UploadManager {

    var httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private var retrofit = Retrofit.Builder()
        .baseUrl("http://51.195.19.222/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(ProfileImageService::class.java)
}