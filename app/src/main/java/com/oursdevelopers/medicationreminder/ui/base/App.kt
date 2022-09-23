package com.oursdevelopers.medicationreminder.ui.base

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        const val isDebugBuild = true
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}