package com.example.taskmanager.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.data.repository.UserRepository
import com.example.taskmanager.ui.entry.LoginViewModel

class CustomViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(userRepository) as T
        }
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(userRepository) as T
        }
        else {
            modelClass.newInstance()
        }
    }
}