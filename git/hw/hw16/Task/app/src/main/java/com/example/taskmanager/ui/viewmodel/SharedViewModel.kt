package com.example.taskmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.repository.UserRepository

class SharedViewModel(private val repository: UserRepository) : ViewModel() {



    fun getAllUserTasks(userName: String): LiveData<List<Task>> {
        return repository.getAllUserTask(userName)
    }

    fun addNewTask(task: Task) {
        repository.addNewTask(task)
    }
}