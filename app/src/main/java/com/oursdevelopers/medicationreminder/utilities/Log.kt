package com.oursdevelopers.medicationreminder.utilities

import android.util.Log
import com.oursdevelopers.medicationreminder.ui.base.App

class Log {
    companion object {

        fun i(tag: String?, string: String?) {
            if (App.isDebugBuild && string != null) Log.i(tag, string)
        }

        fun e(tag: String?, string: String?) {
            if (App.isDebugBuild && string != null) Log.e(tag, string)
        }

        fun e(tag: String?, string: String?, cause: Throwable?) {
            if (App.isDebugBuild && string != null) Log.e(tag, string, cause)
        }

        fun d(tag: String?, string: String?) {
            if (App.isDebugBuild && string != null) Log.d(tag, string)
        }

        fun v(tag: String?, string: String?) {
            if (App.isDebugBuild && string != null) Log.v(tag, string)
        }

        fun w(tag: String?, string: String?) {
            if (App.isDebugBuild && string != null) Log.w(tag, string)
        }
    }
}