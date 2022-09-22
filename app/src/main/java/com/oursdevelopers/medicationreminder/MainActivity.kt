package com.oursdevelopers.medicationreminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.oursdevelopers.medicationreminder.databinding.ActivityMainBinding
import com.oursdevelopers.medicationreminder.ui.HomeFragment
import com.oursdevelopers.medicationreminder.ui.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.item_home -> loadFragment(HomeFragment())
                R.id.item_settings -> loadFragment(SettingsFragment())
            }

            return@setOnItemSelectedListener true
        }


    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}