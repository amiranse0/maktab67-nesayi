package com.example.quizretrofit

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {

    private val client = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://www.flickr.com/services/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: GetImageInterface = retrofit.create(GetImageInterface::class.java)
}