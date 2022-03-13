package com.example.taskmanager.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.model.User

interface DataSource {
    //task
    fun addNewTask(task:Task)
    fun showAllUserTask(userName:String):LiveData<List<Task>>
    fun deleteTask(task: Task)
    fun updateTask(task: Task)
    //user
    fun addNewUser(user: User)
    fun deleteUser(user: User)
    fun updateUser(user: User)
    fun getUser(userName:String): LiveData<User>
}