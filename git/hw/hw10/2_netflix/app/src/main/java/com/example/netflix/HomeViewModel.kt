package com.example.netflix

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.netflix.getmoviewithretrofit.Movie
import com.example.netflix.getmoviewithretrofit.MovieNames
import com.example.netflix.getmoviewithretrofit.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var images = MutableLiveData<MutableList<MyImage>?>(null)

    fun getImages() = images

    fun getPosters() {
        var listMovies = mutableListOf<MyImage>()
        for (item in MovieNames().movies) {
            val movie = NetworkManager.service.getMovie(item.key, item.value, "aac9ff3c")
            movie.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    var movie = MyImage(response.body()!!.Title, response.body()!!.Poster, false)
                    listMovies.add(movie)
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                }
            })
        }
        images.value = listMovies
    }


    fun addNewImage(response: Response<Movie>){
        var image = MyImage(response.body()!!.Title, response.body()!!.Poster, false)

        if (images.value == null) {
            var firstNewImage = MutableLiveData(mutableListOf(image))
            images = firstNewImage
        }
        else images.value?.add(image)
    }

    fun clickFavourite(pos: Int): Boolean? {
        images.value?.get(pos)?.isFavorite = images.value?.get(pos)?.isFavorite == false
        return images.value?.get(pos)?.isFavorite
    }
}