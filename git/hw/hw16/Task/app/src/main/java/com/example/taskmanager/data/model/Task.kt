package com.example.taskmanager.data.model

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    var situationOfTask: SituationOfTask,
    var picture:ByteArray? = null,
    val userUserName:String
)
