package com.oursdevelopers.medicationreminder.utilities

import android.content.Context
import android.content.SharedPreferences
import com.oursdevelopers.medicationreminder.ui.base.App.Companion.appContext

class Storage {

    companion object {
        //region KEYS
        const val storageFile = "oursdevelopers_medreminder" //SharePrefs File Name
        const val isNightModeOn = "isNightModeOn" //UserConfig for night mode
        //endregion

        private val settings: SharedPreferences = appContext.getSharedPreferences(storageFile, Context.MODE_PRIVATE)
        private val editor = settings.edit()

        fun storeLocal(key: String, value: Boolean) {
            try {
                editor.putBoolean(key, value)
                editor.apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun fetchLocal(key: String): Boolean {
            try {
                return settings.getBoolean(key, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }
    }

}