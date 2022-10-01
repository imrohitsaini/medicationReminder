package com.oursdevelopers.medicationreminder.ui.addmeds

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intuit.sdp.R.dimen.*
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.ui.theme.SettingsTypography
import com.oursdevelopers.medicationreminder.ui.theme.TextFieldStyle
import com.oursdevelopers.medicationreminder.utilities.Utils

@Composable
fun AddMedsCompose() {
    val context = LocalContext.current
    val medicineName = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            Box {
                AppBar(context)
            }
            Box(
                modifier = Modifier.padding(
                    dimensionResource(id = _15sdp).value.dp,
                    dimensionResource(id = _20sdp).value.dp
                )
            ) {
                AddRemForm(medicineName)
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    dimensionResource(id = _15sdp),
                )
        ) {
            SetReminder(context, medicineName)
        }
    }
}

@Composable
fun AppBar(context: Context) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = _45sdp))
            .background(color = colorResource(id = R.color.primary))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
        ) {
            Icon(
                tint = colorResource(id = R.color.onPrimary),
                painter = painterResource(id = R.drawable.ic_back_icon),
                contentDescription = stringResource(id = R.string.go_back),
                modifier = Modifier
                    .padding(dimensionResource(id = _10sdp), 0.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        (context as Activity).finish()
                    }
            )
            Text(
                text = "Add Schedule",
                color = colorResource(id = R.color.onPrimary),
                style = SettingsTypography.subtitle1,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun AddRemForm(medicineName: MutableState<String>) {
    val maxCharMedicineName = 20

    Column(modifier = Modifier.fillMaxWidth()) {
        TextBox("Medicine Name", medicineName, maxCharMedicineName)
    }
}

@Composable
fun TextBox(title: String, text: MutableState<String>, maxChar: Int) {
    Column {
        Text(
            text = title,
            color = colorResource(id = R.color.textColorHeading),
            style = TextFieldStyle.body2
        )
        Card(
            shape = RoundedCornerShape(dimensionResource(id = _10sdp)),
            elevation = dimensionResource(id = _2sdp),
            modifier = Modifier
                .padding(
                    0.dp,
                    dimensionResource(id = _10sdp),
                    0.dp,
                    0.dp,
                )
        ) {
            TextField(
                value = text.value,
                singleLine = true,
                onValueChange = { newText ->
                    if (newText.length <= maxChar)
                        text.value = newText
                    else if (newText.length > maxChar)
                        text.value = newText.take(maxChar)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pill),
                        contentDescription = "Medicine Name",
                        tint = colorResource(id = R.color.primary),
                    )
                },
                textStyle = TextFieldStyle.subtitle1,
                placeholder = {
                    Text(
                        text = "e.g Wysolne 5mg, Tobamist 5ml",
                        fontStyle = FontStyle.Italic,
                        style = TextFieldStyle.subtitle2,
                        color = colorResource(id = R.color.placeHolderColor)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colorResource(id = R.color.textBoxTextColor),
                    cursorColor = colorResource(id = R.color.cursorColor),
                    backgroundColor = colorResource(id = R.color.textBoxBackground),
                    focusedIndicatorColor = colorResource(id = R.color.transparent),
                    unfocusedIndicatorColor = colorResource(id = R.color.transparent),
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun SetReminder(context: Context, medicineName: MutableState<String>) {
    Button(
        onClick = {
            Utils.shortToast(context, medicineName.value)
        },
        shape = RoundedCornerShape(dimensionResource(id = _10sdp)),
        colors = ButtonDefaults
            .buttonColors(
                contentColor = colorResource(id = R.color.onPrimary),
                backgroundColor = colorResource(id = R.color.primary)
            ),
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = _50sdp))
    ) {
        Text(
            text = "Set Schedule",
            color = colorResource(id = R.color.onPrimary),
            style = SettingsTypography.subtitle1,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Surface(Modifier.fillMaxSize()) {
        AddMedsCompose()
    }
}