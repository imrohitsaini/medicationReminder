package com.oursdevelopers.medicationreminder.utilities

import android.content.Context
import android.content.SharedPreferences
import com.oursdevelopers.medicationreminder.ui.base.App.Companion.appContext
import com.oursdevelopers.medicationreminder.ui.base.App.Companion.storageFile

class Storage {

    companion object {
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