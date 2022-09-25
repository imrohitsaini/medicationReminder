package com.oursdevelopers.medicationreminder.ui.mainfragments.screens.mymeds

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intuit.sdp.R.dimen.*
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.ui.theme.SettingsTypography

@Composable
fun MyMedsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            AddMedsFAB()
        }
    }
}

@Composable
fun AddMedsFAB() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                0.dp,
                0.dp,
                dimensionResource(id = _20sdp).value.dp,
                dimensionResource(id = _20sdp).value.dp,
            )
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            backgroundColor = colorResource(id = R.color.primary),
            contentColor = colorResource(id = R.color.onPrimary),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_meds),
                contentDescription = stringResource(id = R.string.add_medicine)
            )
        }
        Text(
            text = stringResource(id = R.string.add_medicine),
            color = colorResource(id = R.color.textColorHeading),
            style = SettingsTypography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    0.dp,
                    dimensionResource(id = _7sdp).value.dp,
                    0.dp,
                    0.dp,
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Surface(Modifier.fillMaxSize()) {
        MyMedsScreen()
    }
}