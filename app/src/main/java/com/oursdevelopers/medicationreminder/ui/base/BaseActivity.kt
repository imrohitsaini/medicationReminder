package com.oursdevelopers.medicationreminder.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.oursdevelopers.medicationreminder.utilities.Storage
import com.oursdevelopers.medicationreminder.utilities.Storage.Companion.isNightModeOn

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(bundle: Bundle?) {
        if (Storage.fetchLocal(isNightModeOn)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        super.onCreate(bundle)
    }

}