package com.example.taskmanager.ui.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskmanager.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}