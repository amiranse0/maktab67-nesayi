package com.example.quiz


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var counter = 0
    private var questions = listOf(
            Question(
                "liverpool won champions league 6 times.", true,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "manchester united won champions league 4 times.", false,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "Messi has scored more than Ronaldo.", false,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "Messi has won 8 ballon d'or.", true,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "Iran won Asian Cup 3 times.", true,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "France won world cup one times.", false,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "Dani Alves never played for Barcelona.", false,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "Harry Kane is capitan of England football team.", true,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "Perspolis won the Asian Champions League.", false,
                isAnsweredYet = false, isCheating = false
            ),
            Question(
                "Esteghlal won the Asian Champions league two times.", true,
                isAnsweredYet = false, isCheating = false
            ),
        )

    fun getCounter() = counter

    fun getQuestion() = questions[counter].question

    fun getQuestionSize() = questions.size

    fun getAnswer() = questions[counter].answer


    fun nextQuestion() {
        counter++
    }

    fun previousQuestion() {
        counter--
    }

    fun answered() {
        questions[counter].isAnsweredYet = true
    }

    fun cheated() {
        questions[counter].isCheating = true
    }

    fun isAnswered(): Boolean = questions[counter].isAnsweredYet

    fun isCheating(): Boolean = questions[counter].isCheating
}