package com.example.netflix.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.netflix.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NetflixActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_flix)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment)

        val appBrConfig = AppBarConfiguration(setOf(
            R.id.fragmentProfile,
            R.id.fragmentFavorite,
            R.id.fragmentComingSoon,
            R.id.fragmentHome
        ))

        setupActionBarWithNavController(navController,appBrConfig)

        bottomNavigationView.setupWithNavController(navController)
    }
}