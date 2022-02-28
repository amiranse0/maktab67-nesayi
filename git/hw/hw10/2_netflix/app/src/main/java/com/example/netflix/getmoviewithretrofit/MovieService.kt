package com.example.netflix.getmoviewithretrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("https://www.omdbapi.com/")
    fun getMovie(@Query("t") names: String,
    @Query("y") year:String,
    @Query("apikey") api:String): Call<Movie>

    @GET("https://www.omdbapi.com/?t=%20the%20godfather&y=1972&apikey=aac9ff3c")
    fun test():Call<Movie>
}