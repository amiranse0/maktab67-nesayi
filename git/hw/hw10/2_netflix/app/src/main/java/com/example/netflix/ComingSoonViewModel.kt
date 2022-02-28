package com.example.netflix

import android.util.Log
import androidx.lifecycle.ViewModel

class ComingSoonViewModel:ViewModel() {

    val comingSoonMovies = listOf(Triple("red notice",  "2021",
        "https://www.imdb.com/title/tt7991608/?ref_=fn_al_tt_1"),
        Triple("Nightbooks",  "2021",
            "https://www.imdb.com/title/tt10521144/?ref_=fn_al_tt_1"),
        Triple("Mother/Android",  "2021",
            "https://www.imdb.com/title/tt13029044/?ref_=fn_al_tt_1")
    )

    var listNewMovies = mutableListOf<ResultNewMovies>()

    fun addNewPoster(movie:ResultNewMovies){

        Log.d("TAG", "add new poster just called! ${listNewMovies.size}")

        listNewMovies.add(movie)
    }
}