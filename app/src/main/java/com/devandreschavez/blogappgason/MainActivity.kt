package com.devandreschavez.blogappgason

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.devandreschavez.blogappgason.databinding.ActivityMainBinding

import com.devandreschavez.blogappgason.core.hide
import com.devandreschavez.blogappgason.core.show

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.loginFragment -> {
                    binding.bottomNavigationView.hide()
                }
                R.id.registerFragment -> {
                    binding.bottomNavigationView.hide()
                }
                else -> {
                    binding.bottomNavigationView.show()
                }
            }
        }
    }
}