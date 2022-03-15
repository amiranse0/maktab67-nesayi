package com.example.taskmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.repository.UserRepository

class SharedViewModel(private val repository: UserRepository) : ViewModel() {

    private var _timeLiveData = MutableLiveData<String>()
    val timeLiveData = _timeLiveData
    private var _dateLiveData = MutableLiveData<String>()
    val dateLiveData = _dateLiveData

    fun getAllUserTasks(userName: String): LiveData<List<Task>> {
        return repository.getAllUserTask(userName)
    }

    fun addNewTask(task: Task) {
        repository.addNewTask(task)
    }
}