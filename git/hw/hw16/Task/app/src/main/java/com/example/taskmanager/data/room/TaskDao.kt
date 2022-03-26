package com.example.taskmanager.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task

@Dao
interface TaskDao {
    @Insert
    fun addNewTask(task: Task)

    @Query("SELECT * FROM TASK WHERE TASK.userUserName == :userName and TASK.situationOfTask == :situationOfTask")
    fun getUserTask(userName: String, situationOfTask: SituationOfTask): LiveData<List<Task>>

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Update
    fun setIamge(task: Task)

    @Query("SELECT * FROM TASK WHERE TASK.userUserName == :userName")
    fun getAllTask(userName: String): LiveData<List<Task>>

    @Query("SELECT * FROM TASK WHERE (TASK.title LIKE :searchQuery OR TASK.description LIKE :searchQuery) AND TASK.situationOfTask == :situationOfTask")
    fun searchQuery(searchQuery: String, situationOfTask: SituationOfTask): LiveData<List<Task>>

}