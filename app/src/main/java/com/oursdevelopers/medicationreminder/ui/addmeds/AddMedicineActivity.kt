package com.oursdevelopers.medicationreminder.ui.addmeds

import android.os.Bundle
import androidx.activity.compose.setContent
import com.oursdevelopers.medicationreminder.ui.base.BaseActivity

class AddMedicineActivity : BaseActivity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContent {
            AddMedsCompose()
        }
    }
}