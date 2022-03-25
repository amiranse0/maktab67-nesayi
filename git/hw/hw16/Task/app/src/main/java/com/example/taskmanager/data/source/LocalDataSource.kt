package com.example.taskmanager.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskmanager.data.DataSource
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.model.User
import com.example.taskmanager.data.room.TaskDao
import com.example.taskmanager.data.room.UserDao

class LocalDataSource(private val userDao: UserDao, private val taskDao: TaskDao):DataSource {
    override fun addNewTask(task: Task) {
        taskDao.addNewTask(task)
    }

    override fun getUserTask(userName: String, situationOfTask: SituationOfTask): LiveData<List<Task>> {
        return taskDao.getUserTask(userName, situationOfTask)
    }

    override fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override fun addNewUser(user: User) {
        userDao.addNewUser(user)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override fun getUser(userName: String, passWord:String): LiveData<List<User>> {
        return userDao.getUser(userName, passWord)
    }

    override fun getUserWithUsername(userName: String): LiveData<List<User>> {
        return userDao.getUserWithUserName(userName)
    }

}