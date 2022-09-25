package com.oursdevelopers.medicationreminder.ui.mainfragments.screens.mymeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.oursdevelopers.medicationreminder.ui.mainfragments.screens.settings.SettingScreen
import com.oursdevelopers.medicationreminder.ui.mainfragments.screens.settings.SettingsFragment

class MyMedsFragment : Fragment() {

    //region
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MyMedsScreen()
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
            MyMedsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
//endregion

}

