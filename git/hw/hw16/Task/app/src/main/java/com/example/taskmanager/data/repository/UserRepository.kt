package com.example.taskmanager.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskmanager.data.DataSource
import com.example.taskmanager.data.UserNameClass
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.model.User
import java.security.cert.LDAPCertStoreParameters
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

    fun getUserTask(userName:String, situationOfTask: SituationOfTask): LiveData<List<Task>>{
        return localDataSource.getUserTask(userName, situationOfTask)
    }

    fun setImage(task: Task){
        executorService.submit {
            localDataSource.setImageForTask(task)
        }
    }

    fun updateTask(task: Task){
        executorService.submit {
            localDataSource.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        executorService.submit {
            localDataSource.deleteTask(task)
        }
    }

    fun getAllTasks(userName: String):LiveData<List<Task>>{
        return localDataSource.getAllTask(userName)
    }

    fun searchQuery(searchQuery: String): LiveData<List<Task>>{
        return localDataSource.searchQuery(searchQuery)
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

    fun getUser(userName: String, passWord:String):LiveData<List<User>>{
        return localDataSource.getUser(userName,passWord)
    }

    fun getUserWithUsername(userName: String):LiveData<List<User>>{
        return localDataSource.getUserWithUsername(userName)
    }

}