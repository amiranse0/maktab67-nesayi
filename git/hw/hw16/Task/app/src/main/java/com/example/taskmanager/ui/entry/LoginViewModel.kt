package com.example.taskmanager.ui.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.taskmanager.data.model.User
import com.example.taskmanager.data.repository.UserRepository

class LoginViewModel(private val repository: UserRepository):ViewModel() {

    fun createUser(user: User){
        repository.addNewUser(user)
    }

    fun getUserUserName(userName:String, passWord:String):LiveData<String>{
        val _username = repository.getUser(userName, passWord).map {
            var username = ""
            if (it.size == 1) username = it[0].userName
            username
        }
        return _username
    }

}