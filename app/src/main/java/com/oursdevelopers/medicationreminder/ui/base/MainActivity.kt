package com.oursdevelopers.medicationreminder.ui.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.databinding.ActivityMainBinding
import com.oursdevelopers.medicationreminder.ui.mainfragments.screens.home.HomeFragment
import com.oursdevelopers.medicationreminder.ui.mainfragments.screens.settings.SettingsFragment
import com.oursdevelopers.medicationreminder.utilities.Utils

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isBackPressedTwice = false
    private var selectedFragment = R.id.item_home

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.item_home -> loadFragment(HomeFragment())
                R.id.item_settings -> loadFragment(SettingsFragment())
            }

            return@setOnItemSelectedListener true
        }

        if (intent.hasExtra("selectedFragment"))
            selectedFragment = intent.getIntExtra("selectedFragment", selectedFragment)

        binding.bottomNav.selectedItemId = selectedFragment

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (binding.bottomNav.selectedItemId == R.id.item_settings) {
            binding.bottomNav.selectedItemId = R.id.item_home
        } else if (binding.bottomNav.selectedItemId == R.id.item_home) {
            if (isBackPressedTwice) {
                super.onBackPressed()
                return
            }

            Utils.shortToast(this, "Press back again to exit")
            isBackPressedTwice = true
            Handler(Looper.getMainLooper()).postDelayed({ isBackPressedTwice = false }, 2000)
        }
    }
}