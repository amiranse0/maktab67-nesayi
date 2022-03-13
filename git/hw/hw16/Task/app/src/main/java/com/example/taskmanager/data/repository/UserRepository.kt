package com.example.taskmanager.data.repository

import androidx.lifecycle.LiveData
import com.example.taskmanager.data.DataSource
import com.example.taskmanager.data.model.Task
import java.util.concurrent.ExecutorService

class UserRepository(
    private val executorService: ExecutorService,
    private val localDataSource: DataSource
) {
    fun addNewTask(task:Task){
        executorService.submit{
            localDataSource.addNewTask(task)
        }
    }

    fun getAllUserTask(userName:String): LiveData<List<Task>>{
        return localDataSource.showAllUserTask(userName)
    }
}