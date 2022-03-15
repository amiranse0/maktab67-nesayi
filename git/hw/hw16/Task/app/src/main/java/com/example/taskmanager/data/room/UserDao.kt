package com.example.taskmanager.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.taskmanager.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = ABORT)
    fun addNewUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE userName == :userName")
    fun getUser(userName:String): LiveData<List<User>>
}