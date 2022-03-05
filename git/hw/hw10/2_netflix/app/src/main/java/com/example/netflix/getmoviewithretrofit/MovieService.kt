package com.example.netflix.getmoviewithretrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieService {
    @GET("https://imdb-api.com/en/API/AdvancedSearch/{apiKey}")
    fun getMovieFromServer(@Path("apiKey") apiKey:String,
    @QueryMap hashMap: HashMap<String,String>): Call<Movie>
}