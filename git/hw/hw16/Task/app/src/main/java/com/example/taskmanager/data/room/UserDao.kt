package com.example.taskmanager.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.taskmanager.data.model.User

@Dao
interface UserDao {
    @Insert()
    fun addNewUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE userName == :userName AND password == :passWord")
    fun getUser(userName:String, passWord:String): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE userName == :userName")
    fun getUserWithUserName(userName: String): LiveData<List<User>>
}