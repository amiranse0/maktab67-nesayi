package com.example.taskmanager.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.UserNameClass
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.repository.UserRepository

class SharedViewModel(private val repository: UserRepository) : ViewModel() {

    var fragmentNameLiveData = MutableLiveData("TODO")

    fun setFragmentName(fragmentName:String){
        fragmentNameLiveData.postValue(fragmentName)
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

    fun updateTask(task: Task){
        repository.updateTask(task)
    }

    fun deleteTask(task: Task){
        repository.deleteTask(task)
    }

    fun getAllTask(username: String):LiveData<List<Task>>{
        return repository.getAllTasks(username)
    }

    fun searchQuery(searchSuery: String): LiveData<List<Task>>{
        var situation = SituationOfTask.TODO
        when(fragmentNameLiveData.value){
            "DONE" -> situation = SituationOfTask.DONE
            "DOING" -> situation = SituationOfTask.DOING
            "TODO" -> situation = SituationOfTask.TODO
        }
        return repository.searchQuery(searchSuery,situation)
    }

}