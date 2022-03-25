package com.example.taskmanager.ui.entry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.taskmanager.data.model.User
import com.example.taskmanager.data.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    fun createUser(user: User) {
        repository.addNewUser(user)
    }

    fun checkForConflict(userName: String):LiveData<Boolean>{
        val _isConflict = repository.getUserWithUsername(userName).map {
            var isConflict = true
            if (it.isEmpty()) isConflict = false
            isConflict
        }

        return _isConflict
    }

    fun getUserUserName(userName: String, passWord: String): LiveData<String> {
        val _username = repository.getUser(userName, passWord).map {
            var username = ""
            if (it.size == 1) username = it[0].userName
            username
        }

        return _username
    }

}