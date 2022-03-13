package com.example.taskmanager

import android.app.Application
import com.example.taskmanager.di.ServiceLocator

class App: Application() {

    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(this)
    }
}