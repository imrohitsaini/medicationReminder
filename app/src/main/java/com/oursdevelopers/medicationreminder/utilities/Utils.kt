package com.oursdevelopers.medicationreminder.utilities

import android.content.Context
import android.widget.Toast

class Utils {
    companion object {
        fun shortToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

        fun longToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }

}