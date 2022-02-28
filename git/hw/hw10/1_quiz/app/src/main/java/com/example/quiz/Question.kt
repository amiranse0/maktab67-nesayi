package com.example.quiz

data class Question(
    val question: String,
    val answer: Boolean,
    var isAnsweredYet: Boolean,
    var isCheating: Boolean
)