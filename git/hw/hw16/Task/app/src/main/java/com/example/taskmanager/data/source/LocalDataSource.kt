package com.example.taskmanager.data.source

import androidx.lifecycle.LiveData
import com.example.taskmanager.data.DataSource
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.model.User
import com.example.taskmanager.data.room.TaskDao
import com.example.taskmanager.data.room.UserDao

class LocalDataSource(private val userDao: UserDao, private val taskDao: TaskDao):DataSource {
    override fun addNewTask(task: Task) {
        taskDao.addNewTask(task)
    }

    override fun showAllUserTask(userName: String): LiveData<List<Task>> {
        return taskDao.showAllUserTask(userName)
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

    override fun getUser(userName: String): LiveData<List<User>> {
        return userDao.getUser(userName)
    }

}