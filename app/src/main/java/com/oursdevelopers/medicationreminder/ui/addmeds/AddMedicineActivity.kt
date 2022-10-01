package com.oursdevelopers.medicationreminder.ui.addmeds

import android.os.Bundle
import androidx.activity.compose.setContent
import com.oursdevelopers.medicationreminder.ui.base.BaseComponentActivity

class AddMedicineActivity : BaseComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddMedsCompose()
        }
    }
}