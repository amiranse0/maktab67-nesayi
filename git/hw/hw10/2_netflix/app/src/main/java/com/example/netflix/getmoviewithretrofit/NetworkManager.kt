package com.example.netflix.getmoviewithretrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    var httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private var retrofit = Retrofit.Builder()
        .baseUrl("https://imdb-api.com/en/API/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(MovieService::class.java)
}