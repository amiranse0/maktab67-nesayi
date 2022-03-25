package com.example.taskmanager.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.repository.UserRepository

class SharedViewModel(private val repository: UserRepository) : ViewModel() {

    var username = ""
        get() = field
        set(value) {field = value}

    private var _fragmentNameLiveData = MutableLiveData("TODO")
    var fragmentNameLiveData = _fragmentNameLiveData

    fun setFragmentName(fragmentName:String){
        _fragmentNameLiveData.postValue(fragmentName)
    }

    fun addNewTask(task: Task) {
        repository.addNewTask(task)
    }

    fun getTasks(username:String, situationOfTask: SituationOfTask):LiveData<List<Task>>{
        return repository.getUserTask(username, situationOfTask)
    }

    fun setImage(task: Task){
        repository.setImage(task)
    }

}