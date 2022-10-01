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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import com.intuit.sdp.R.dimen.*
import com.oursdevelopers.medicationreminder.R
import com.oursdevelopers.medicationreminder.ui.theme.SettingsTypography
import com.oursdevelopers.medicationreminder.ui.theme.TextFieldStyle
import com.oursdevelopers.medicationreminder.utilities.Log
import com.oursdevelopers.medicationreminder.utilities.Utils

@Composable
fun AddMedsCompose() {
    val context = LocalContext.current
    val medicineNameText = remember { mutableStateOf("") }

    val frequencyExpanded = remember { mutableStateOf(false) }
    val frequencyItems = listOf(
        stringResource(id = R.string.frequency_item_daily),
        stringResource(id = R.string.frequency_item_alt),
        stringResource(id = R.string.frequency_item_weekly),
        stringResource(id = R.string.frequency_item_monthly),
        stringResource(id = R.string.frequency_item_custom),
    )
    val frequencyTextSize = remember { mutableStateOf(Size.Zero) }
    val frequencyText = remember { mutableStateOf(frequencyItems[0]) }

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
                AppBar(context, stringResource(R.string.add_scheule))
            }
            Box(
                modifier = Modifier.padding(
                    dimensionResource(id = _15sdp).value.dp,
                    dimensionResource(id = _20sdp).value.dp
                )
            ) {
                AddRemForm(medicineNameText, frequencyExpanded, frequencyItems, frequencyTextSize, frequencyText)
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    dimensionResource(id = _15sdp),
                )
        ) {
            SetReminder(context, medicineNameText)
        }
    }
}

@Composable
fun AppBar(context: Context, activityName: String) {
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
                text = activityName,
                color = colorResource(id = R.color.onPrimary),
                style = SettingsTypography.subtitle1,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun AddRemForm(medicineName: MutableState<String>, frequencyExpanded: MutableState<Boolean>, frequencyItems: List<String>, frequencyTextSize: MutableState<Size>, frequencyText: MutableState<String>) {
    val maxCharMedicineName = 20
    Column(modifier = Modifier.fillMaxWidth()) {
        TextBoxWithLeadingIcon(
            label = stringResource(R.string.medicine_name),
            placeholder = stringResource(R.string.medicine_name_placeholder),
            mutableText = medicineName,
            maxChar = maxCharMedicineName,
            leadingIcon = painterResource(id = R.drawable.ic_pill)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 0.dp,
                    top = dimensionResource(id = _15sdp),
                    end = 0.dp,
                    bottom = 0.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .weight(weight = 0.55f, fill = true)
            ) {
                DropDownFrequency(
                    label = stringResource(R.string.frequency),
                    placeholder = stringResource(id = R.string.empty),
                    trailingIcon = painterResource(id = R.drawable.ic_drop_down),
                    frequencyExpanded, frequencyItems, frequencyTextSize, frequencyText
                )
            }
            Box(
                modifier = Modifier
                    .weight(weight = 0.45f, fill = true)
                    .padding(
                        start = dimensionResource(id = _15sdp),
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
            ) {
                DropDown(
                    label = stringResource(R.string.doses),
                    placeholder = stringResource(id = R.string.empty),
                    mutableText = medicineName,
                    trailingIcon = painterResource(id = R.drawable.ic_drop_down)
                )
            }
        }
    }
}

@Composable
fun TextBoxWithLeadingIcon(label: String, placeholder: String, mutableText: MutableState<String>, maxChar: Int, leadingIcon: Painter) {
    Column {
        Text(
            text = label,
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
                value = mutableText.value,
                singleLine = true,
                onValueChange = { newText ->
                    if (newText.length <= maxChar)
                        mutableText.value = newText
                    else if (newText.length > maxChar)
                        mutableText.value = newText.take(maxChar)
                },
                leadingIcon = {
                    Icon(
                        painter = leadingIcon,
                        contentDescription = label,
                        tint = colorResource(id = R.color.primary),
                    )
                },
                textStyle = TextFieldStyle.subtitle1,
                placeholder = {
                    Text(
                        text = placeholder,
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
fun DropDown(label: String, placeholder: String, mutableText: MutableState<String>, trailingIcon: Painter) {
    Column {
        Text(
            text = label,
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
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    Log.e("testing", "clicking dropdown")
                }
        ) {
            TextField(
                value = mutableText.value,
                enabled = false,
                singleLine = true,
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        painter = trailingIcon,
                        contentDescription = label,
                        tint = colorResource(id = R.color.primary),
                    )
                },
                textStyle = TextFieldStyle.subtitle1,
                placeholder = {
                    Text(
                        text = placeholder,
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
                    disabledIndicatorColor = colorResource(id = R.color.transparent),
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}

@Composable
fun DropDownFrequency(
    label: String,
    placeholder: String,
    trailingIcon: Painter,
    frequencyExpanded: MutableState<Boolean>,
    frequencyItems: List<String>,
    frequencyTextSize: MutableState<Size>,
    frequencyText: MutableState<String>
) {
    Column {
        Text(
            text = label,
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
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    frequencyTextSize.value = coordinates.size.toSize()
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    Log.e("testing", "clicking dropdown " + frequencyExpanded.value)
                    frequencyExpanded.value = !frequencyExpanded.value
                }
        ) {
            TextField(
                value = frequencyText.value,
                enabled = false,
                singleLine = true,
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        painter = trailingIcon,
                        contentDescription = label,
                        tint = colorResource(id = R.color.primary),
                    )
                },
                textStyle = TextFieldStyle.subtitle1,
                placeholder = {
                    Text(
                        text = placeholder,
                        fontStyle = FontStyle.Italic,
                        style = TextFieldStyle.subtitle2,
                        color = colorResource(id = R.color.placeHolderColor)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colorResource(id = R.color.textBoxTextColor),
                    disabledTextColor = colorResource(id = R.color.textBoxTextColor),
                    cursorColor = colorResource(id = R.color.cursorColor),
                    backgroundColor = colorResource(id = R.color.textBoxBackground),
                    focusedIndicatorColor = colorResource(id = R.color.transparent),
                    unfocusedIndicatorColor = colorResource(id = R.color.transparent),
                    disabledIndicatorColor = colorResource(id = R.color.transparent),
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
            MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(dimensionResource(id = _10sdp)))) {
                DropdownMenu(
                    expanded = frequencyExpanded.value,
                    offset = DpOffset(0.dp, dimensionResource(id = _5sdp)),
                    onDismissRequest = { frequencyExpanded.value = false },
                    properties = PopupProperties(clippingEnabled = false),
                    modifier = Modifier
                        .width(with(LocalDensity.current) { frequencyTextSize.value.width.toDp() })
                        .background(color = colorResource(id = R.color.textBoxBackground))
                ) {
                    frequencyItems.forEach { label ->
                        DropdownMenuItem(
                            onClick = {
                                frequencyText.value = label
                                frequencyExpanded.value = false
                            }
                        ) {
                            if (label.equals(stringResource(id = R.string.frequency_item_custom), true)) {
                                Row {
                                    Text(
                                        text = label,
                                        style = TextFieldStyle.subtitle2,
                                        color = colorResource(id = R.color.textBoxTextColor),
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_edit),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.primary),
                                    )
                                }
                            } else {
                                Text(
                                    text = label,
                                    style = TextFieldStyle.subtitle2,
                                    color = colorResource(id = R.color.textBoxTextColor),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SetReminder(context: Context, medicineName: MutableState<String>) {
    MyButton(context, R.string.set_schedule, medicineName)
}

@Composable
fun MyButton(context: Context, buttonName: Int, medicineName: MutableState<String>) {
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
            text = stringResource(buttonName),
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