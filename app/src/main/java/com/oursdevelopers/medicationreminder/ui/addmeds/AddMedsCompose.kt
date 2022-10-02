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
    val onSelectingFrequency: (index: Int) -> Unit = {
        frequencyText.value = frequencyItems[it]
        frequencyExpanded.value = false
    }

    val doseExpanded = remember { mutableStateOf(false) }
    val doseItems = listOf(
        stringResource(id = R.string.dose_item_1),
        stringResource(id = R.string.dose_item_2),
        stringResource(id = R.string.dose_item_3),
        stringResource(id = R.string.dose_item_4),
        stringResource(id = R.string.dose_item_custom),
    )
    val doseTextSize = remember { mutableStateOf(Size.Zero) }
    val doseText = remember { mutableStateOf(doseItems[0]) }
    val onSelectingDose: (index: Int) -> Unit = {
        doseText.value = doseItems[it]
        doseExpanded.value = false
    }

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
                AddRemForm(
                    medicineName = medicineNameText,
                    frequencyExpanded = frequencyExpanded,
                    frequencyItems = frequencyItems,
                    frequencyTextSize = frequencyTextSize,
                    frequencyText = frequencyText,
                    onSelectingFrequency = onSelectingFrequency,
                    doseExpanded = doseExpanded,
                    doseItems = doseItems,
                    doseTextSize = doseTextSize,
                    doseText = doseText,
                    onSelectingDose = onSelectingDose,
                )
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
fun AddRemForm(
    medicineName: MutableState<String>,
    frequencyExpanded: MutableState<Boolean>,
    frequencyItems: List<String>,
    frequencyTextSize: MutableState<Size>,
    frequencyText: MutableState<String>,
    onSelectingFrequency: (index: Int) -> Unit,
    doseExpanded: MutableState<Boolean>,
    doseItems: List<String>,
    doseTextSize: MutableState<Size>,
    doseText: MutableState<String>,
    onSelectingDose: (index: Int) -> Unit
) {
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
                DropDownMenu(
                    label = stringResource(R.string.frequency),
                    placeholder = stringResource(id = R.string.empty),
                    trailingIcon = painterResource(id = R.drawable.ic_dropdown_down_arrow),
                    isExpanded = frequencyExpanded,
                    items = frequencyItems,
                    widthSize = frequencyTextSize,
                    text = frequencyText,
                    onSelectingItem = onSelectingFrequency
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
                DropDownMenu(
                    label = stringResource(R.string.doses_per_day),
                    placeholder = stringResource(id = R.string.empty),
                    trailingIcon = painterResource(id = R.drawable.ic_dropdown_down_arrow),
                    isExpanded = doseExpanded,
                    items = doseItems,
                    widthSize = doseTextSize,
                    text = doseText,
                    onSelectingItem = onSelectingDose
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
fun DropDownMenu(
    label: String,
    placeholder: String,
    trailingIcon: Painter,
    isExpanded: MutableState<Boolean>,
    items: List<String>,
    widthSize: MutableState<Size>,
    text: MutableState<String>,
    onSelectingItem: (index: Int) -> Unit
) {

    val icon = if (isExpanded.value) {
        painterResource(id = R.drawable.ic_dropdown_up_arrow)
    } else {
        trailingIcon
    }

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
                    widthSize.value = coordinates.size.toSize()
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    if (!isExpanded.value) {
                        isExpanded.value = true
                    }
                }
        ) {
            TextField(
                value = text.value,
                enabled = false,
                singleLine = true,
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        painter = icon,
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
            MaterialTheme(
                shapes = MaterialTheme
                    .shapes
                    .copy(
                        medium = RoundedCornerShape(
                            dimensionResource(id = _10sdp)
                        )
                    )
            ) {
                DropdownMenu(
                    expanded = isExpanded.value,
                    offset = DpOffset(0.dp, dimensionResource(id = _5sdp)),
                    onDismissRequest = { isExpanded.value = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { widthSize.value.width.toDp() })
                        .background(color = colorResource(id = R.color.textBoxBackground))
                ) {
                    items.forEachIndexed { index, label ->

                        when (index) {
                            //first item
                            0 -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            enabled = true,
                                        ) {
                                            onSelectingItem(index)
                                        }
                                ) {
                                    Text(
                                        text = label,
                                        style = TextFieldStyle.subtitle2,
                                        color = colorResource(id = R.color.textBoxTextColor),
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(
                                                start = dimensionResource(id = _10sdp),
                                                top = dimensionResource(id = _2sdp),
                                                end = dimensionResource(id = _10sdp),
                                                bottom = 0.dp,
                                            )
                                    )
                                }
                            }
                            //last item
                            items.size - 1 -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            enabled = true,
                                        ) {
                                            onSelectingItem(index)
                                        }
                                ) {
                                    Text(
                                        text = label,
                                        style = TextFieldStyle.subtitle2,
                                        color = colorResource(id = R.color.textBoxTextColor),
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(
                                                start = dimensionResource(id = _10sdp),
                                                top = dimensionResource(id = _6sdp),
                                                end = 0.dp,
                                                bottom = dimensionResource(id = _2sdp),
                                            )
                                    )
                                    Spacer(
                                        Modifier
                                            .weight(1f)
                                            .align(Alignment.CenterVertically)
                                            .padding(
                                                start = dimensionResource(id = _10sdp),
                                                top = dimensionResource(id = _6sdp),
                                                end = 0.dp,
                                                bottom = dimensionResource(id = _2sdp),
                                            )
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_edit),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.primary),
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(
                                                start = 0.dp,
                                                top = dimensionResource(id = _6sdp),
                                                end = dimensionResource(id = _10sdp),
                                                bottom = dimensionResource(id = _2sdp),
                                            )
                                    )
                                }
                            }
                            //inbetween items
                            else -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            enabled = true,
                                        ) {
                                            onSelectingItem(index)
                                        }
                                ) {
                                    Text(
                                        text = label,
                                        style = TextFieldStyle.subtitle2,
                                        color = colorResource(id = R.color.textBoxTextColor),
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(
                                                start = dimensionResource(id = _10sdp),
                                                top = dimensionResource(id = _10sdp),
                                                end = dimensionResource(id = _10sdp),
                                                bottom = 0.dp,
                                            )
                                            .fillMaxWidth()
                                    )
                                }
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