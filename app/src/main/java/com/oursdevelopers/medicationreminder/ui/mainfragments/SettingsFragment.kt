package com.oursdevelopers.medicationreminder.ui.mainfragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.databinding.FragmentSettingsBinding
import com.oursdevelopers.medicationreminder.ui.base.BaseFragment
import com.oursdevelopers.medicationreminder.ui.base.MainActivity
import com.oursdevelopers.medicationreminder.utilities.ClickHandler
import com.oursdevelopers.medicationreminder.utilities.Storage

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
), ClickHandler {

    //region ON CREATE VIEW
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inAppearance.handleClick = this

        binding.inAppearance.svNightMode.isChecked = Storage.fetchLocal("isNightModeOn")

        binding.inAppearance.svNightMode.setOnCheckedChangeListener { _, isChecked ->
            Storage.storeLocal("isNightModeOn", isChecked)
            Handler(Looper.getMainLooper()).postDelayed({ restartApp() }, 100)
        }
    }
    //endregion

    //region SWITCH THEM
    private fun restartApp() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.putExtra("selectedFragment", R.id.item_settings)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        requireActivity().finish()
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

    //region ON CLICK
    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {

            binding.inAppearance.clNightMode.id -> binding.inAppearance.svNightMode.isChecked = !binding.inAppearance.svNightMode.isChecked

        }
    }
    //endregion
}