package com.example.taskmanager.di

import android.app.Application
import com.example.taskmanager.data.repository.UserRepository
import com.example.taskmanager.data.room.AppDataBase
import com.example.taskmanager.data.source.LocalDataSource
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ServiceLocator(application: Application) {
    private val localDataSource = LocalDataSource(
        AppDataBase.getDatabase(application).getUserDao(),
        AppDataBase.getDatabase(application).getTaskDao()
    )
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    val repository = UserRepository(executorService, localDataSource)
}