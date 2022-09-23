package com.oursdevelopers.medicationreminder.utilities

import android.view.View

interface ClickHandler {

    fun onClick(v: View) {
        v.isEnabled = false
        v.postDelayed(
            { v.isEnabled = true },
            300
        )
    }
}