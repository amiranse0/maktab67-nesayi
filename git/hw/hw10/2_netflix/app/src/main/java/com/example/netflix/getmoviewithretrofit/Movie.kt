package com.example.netflix.getmoviewithretrofit

data class Movie(
    val errorMessage: Any,
    val queryString: String,
    val results: List<Result>
)