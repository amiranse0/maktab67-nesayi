package com.example.taskmanager.ui.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.taskmanager.data.model.User
import com.example.taskmanager.data.repository.UserRepository

class LoginViewModel(private val repository: UserRepository):ViewModel() {
    private val _usernameLiveData = MutableLiveData<String>()
    var usernameLiveData = _usernameLiveData

    private var _userLiveData = MutableLiveData<User>()
    val userLiveData = _userLiveData

    fun setUser(username: String){
        _usernameLiveData.postValue(username)
    }

    fun getUserFromDataBase(){
        val temp = _usernameLiveData.value?.let {
            repository.getUser(it).map {
                it[0]
            }
        }
        _userLiveData = temp as MutableLiveData<User>
    }
}