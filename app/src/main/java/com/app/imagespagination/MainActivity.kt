package com.app.imagespagination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateGraphToHostContainer()
    }

    private fun inflateGraphToHostContainer() {
        val myNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navigation)
        myNavHostFragment.navController.graph = graph
    }
}