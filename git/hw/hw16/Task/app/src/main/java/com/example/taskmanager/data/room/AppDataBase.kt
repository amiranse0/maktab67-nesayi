package com.example.taskmanager.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.model.User

@Database(entities = [Task::class, User::class], version = 1, exportSchema = true)
abstract class AppDataBase:RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getTaskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}