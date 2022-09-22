package com.oursdevelopers.medicationreminder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.databinding.FragmentSettingsBinding
import com.oursdevelopers.medicationreminder.ui.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {

    //region ON CREATE VIEW
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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