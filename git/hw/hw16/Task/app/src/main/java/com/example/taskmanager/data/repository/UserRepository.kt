package com.example.taskmanager.data.repository

import androidx.lifecycle.LiveData
import com.example.taskmanager.data.DataSource
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.model.User
import java.util.concurrent.ExecutorService

class UserRepository(
    private val executorService: ExecutorService,
    private val localDataSource: DataSource
) {

    //tasks
    fun addNewTask(task:Task){
        executorService.submit{
            localDataSource.addNewTask(task)
        }
    }

    fun getAllUserTask(userName:String): LiveData<List<Task>>{
        return localDataSource.showAllUserTask(userName)
    }

    //users
    fun addNewUser(user: User){
        executorService.submit {
            localDataSource.addNewUser(user)
        }
    }

    fun deleteUser(user: User){
        executorService.submit {
            localDataSource.deleteUser(user)
        }
    }

    fun updateUser(user: User){
        executorService.submit {
            localDataSource.updateUser(user)
        }
    }

    fun getUser(userName: String):LiveData<List<User>>{
        return localDataSource.getUser(userName)
    }
}