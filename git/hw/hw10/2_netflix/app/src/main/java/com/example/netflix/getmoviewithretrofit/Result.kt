package com.example.netflix.getmoviewithretrofit

data class Result(
    val contentRating: Any,
    val description: String,
    val genreList: List<Genre>,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingVotes: String,
    val image: String,
    val metacriticRating: Any,
    val plot: String,
    val runtimeStr: String,
    val starList: List<Star>,
    val stars: String,
    val title: String
)