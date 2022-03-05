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

    private var movies = MutableLiveData<List<MyMovie>>()
    private var newMovies = MutableLiveData<List<MyMovie>>()
    private var favouriteMovies = MutableLiveData<List<MyMovie>>()

    fun getNewMovies() = newMovies
    fun getMovies() = movies
    fun getFavouriteMovies():MutableLiveData<List<MyMovie>>{
        var temp = mutableListOf<MyMovie>()
        for(i in movies.value?: emptyList()){
            if (i.isFavorite) temp.add(i)
        }
        favouriteMovies.postValue(temp)
        return favouriteMovies
    }

    fun getMovieFromServer() {
        if (movies.value == null){
            val movie = NetworkManager.service.getMovieFromServer(
                "k_3w228043",
                hashMapOf("groups" to "top_250", "count" to "21")
            )

            movie.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    movies.postValue(response.body()?.results?.map {
                        MyMovie(it.title, it.image, false)
                    })
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
        }
    }

    fun getNewMoviesFromServer() {

        if (newMovies.value == null){
            val movie = NetworkManager.service.getMovieFromServer(
                "k_3w228043",
                hashMapOf("release_date" to "2022-01-01,2022-03-01", "count" to "3")
            )

            movie.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    newMovies.postValue(response.body()?.results?.map {
                        MyMovie(it.title, it.image, false, it.plot)
                    })
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
        }
    }

    fun clickFavourite(pos: Int):Boolean{
        movies.value?.get(pos)?.isFavorite = movies.value?.get(pos)?.isFavorite == false
        return movies.value?.get(pos)?.isFavorite ?: false
    }

}