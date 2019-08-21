package com.example.forecastmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.forecastmvvm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Fields
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_home)   // set toolbar as action bar

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        // setup bottom navigation view with nav controller
        bottom_nav.setupWithNavController(navController)
        // setup action bar with nav controller
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    // for back button
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
