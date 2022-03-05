package com.example.netflix.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.netflix.MyMovie
import com.example.netflix.getmoviewithretrofit.Movie
import com.example.netflix.getmoviewithretrofit.MovieNames
import com.example.netflix.getmoviewithretrofit.NetworkManager
import com.example.netflix.getmoviewithretrofit.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var movies = MutableLiveData<MutableList<MyMovie>>()
    private var newMovies = MutableLiveData<MutableList<MyMovie>>()

    fun getNewMovies() = newMovies
    fun getMovies() = movies

    fun getMovieFromServer() {
        val result = enqueueRequest(hashMapOf("groups" to "top_250", "count" to "21"))

        val tempList = mutableListOf<MyMovie>()
        for (i in result){
            tempList.add(MyMovie(i.title, i.image, false))
        }
        movies.value?.clear()
        movies.value?.addAll(tempList)
    }

    fun getNewMoviesFromServer(){

        var result = enqueueRequest(hashMapOf("release_date" to "2022-01-01,2022-03-01", "count" to "3"))
        var tempList = mutableListOf<MyMovie>()
        for (i in result){
            tempList.add(MyMovie(i.title, i.image, false))
        }
        newMovies.value?.clear()
        newMovies.value?.addAll(tempList)
    }

    private fun enqueueRequest(hashMap: HashMap<String, String>):List<Result>{
        var result = listOf<Result>()
        val movie = NetworkManager.service.getMovieFromServer("k_3w228043", hashMap)

        movie.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                result = response.body()?.results!!
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })

        return result
    }

    fun clickFavourite(pos: Int): Boolean? {
        movies.value?.get(pos)?.isFavorite = movies.value?.get(pos)?.isFavorite == false
        return movies.value?.get(pos)?.isFavorite
    }

}