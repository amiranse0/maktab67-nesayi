package com.example.taskmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    var situationOfTask: SituationOfTask,
    val userUserName:String
)
