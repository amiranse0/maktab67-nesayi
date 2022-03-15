package com.example.taskmanager.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmanager.data.model.Task

@Dao
interface TaskDao {
    @Insert
    fun addNewTask(task: Task)

    @Query("SELECT * FROM TASK WHERE TASK.userUserName == :userName")
    fun showAllUserTask(userName:String):LiveData<List<Task>>

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

}