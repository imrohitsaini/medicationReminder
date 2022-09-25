package com.oursdevelopers.medicationreminder.ui.mainfragments.screens.settings

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intuit.sdp.R.dimen.*
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.ui.base.MainActivity
import com.oursdevelopers.medicationreminder.ui.theme.SettingsTypography
import com.oursdevelopers.medicationreminder.utilities.Storage

@Composable
fun SettingScreen() {
    Column {
        TextHeading("Appearance")
        LayoutSetting(
            R.drawable.ic_night_mode,
            R.string.settings_night_mode,
            R.string.settings_night_mode_desc,
            Storage.fetchLocal(Storage.isNightModeOn)
        )
    }
}

@Composable
fun TextHeading(txt: String) {
    Row(
        modifier = Modifier.padding(
            dimensionResource(id = _15sdp).value.dp,
            dimensionResource(id = _20sdp).value.dp,
            0.dp,
            0.dp
        )
    ) {
        Text(
            text = txt,
            color = colorResource(id = R.color.textColorHeading),
            style = SettingsTypography.subtitle1
        )
        Divider(
            color = colorResource(id = R.color.dividerLine),
            thickness = 1.dp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(
                    dimensionResource(id = _10sdp).value.dp,
                    0.dp,
                    dimensionResource(id = _15sdp).value.dp,
                    0.dp
                )
        )
    }

}

@Composable
fun LayoutSetting(imageRes: Int, titleRes: Int, descRes: Int, isNightModeOn: Boolean) {
    val context = LocalContext.current
    val nightModeOn = remember { mutableStateOf(isNightModeOn) }
    val rowClickable = remember { mutableStateOf(true) }
    val nightModeSwitch = {
        if (rowClickable.value) {
            rowClickable.value = false
            nightModeOn.value = !nightModeOn.value
            Storage.storeLocal(Storage.isNightModeOn, nightModeOn.value)
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("selectedFragment", R.id.item_settings)
                context.startActivity(intent)
                (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                context.finish()
            }, 100)
            Handler(Looper.getMainLooper()).postDelayed({
                rowClickable.value = true
            }, 500)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = colorResource(id = R.color.textColorHeading)),
                enabled = rowClickable.value,
            ) {
                nightModeSwitch()
            },
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = descRes),
            colorFilter = ColorFilter
                .tint(
                    colorResource(id = R.color.textColorHeading)
                ),
            modifier = Modifier
                .padding(
                    dimensionResource(id = _15sdp).value.dp,
                    dimensionResource(id = _5sdp).value.dp,
                    0.dp,
                    dimensionResource(id = _5sdp).value.dp,
                )
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .padding(
                    dimensionResource(id = _15sdp).value.dp,
                    dimensionResource(id = _5sdp).value.dp,
                )
                .align(Alignment.CenterVertically)
        ) {
            Title(titleRes = titleRes)
            Description(descRes = descRes)
        }
        Spacer(Modifier.weight(1f))
        Switch(
            checked = nightModeOn.value,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorResource(id = R.color.secondary),
                uncheckedThumbColor = Color.White,
                checkedTrackColor = colorResource(id = R.color.textColorHeading),
                uncheckedTrackColor = Color.Gray,
            ),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(
                    0.dp,
                    dimensionResource(id = _5sdp).value.dp,
                    dimensionResource(id = _15sdp).value.dp,
                    dimensionResource(id = _5sdp).value.dp,
                ),
        )
    }
}

@Composable
fun Title(titleRes: Int) {
    Text(
        text = stringResource(id = titleRes),
        color = colorResource(id = R.color.textColorSubheading),
        style = SettingsTypography.body1
    )
}

@Composable
fun Description(descRes: Int) {
    Text(
        text = stringResource(id = descRes),
        color = colorResource(id = R.color.textColorSubheading),
        style = SettingsTypography.body2
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Surface(Modifier.fillMaxSize()) {
        SettingScreen()
    }
}