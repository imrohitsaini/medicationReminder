package com.oursdevelopers.medicationreminder.ui.mainfragments.screens.settings

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.ui.base.MainActivity
import com.oursdevelopers.medicationreminder.utilities.ClickHandler
import com.oursdevelopers.medicationreminder.utilities.Storage
import com.oursdevelopers.medicationreminder.utilities.Storage.Companion.isNightModeOn

class SettingsFragment : Fragment(), ClickHandler {

    //region ON CREATE VIEW
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                SettingScreen()
            }
        }
    }
    //endregion

    //region COMPANAION OBJECT
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SettingsFragment.
         */
        @JvmStatic
        fun newInstance() =
            SettingsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
    //endregion
}