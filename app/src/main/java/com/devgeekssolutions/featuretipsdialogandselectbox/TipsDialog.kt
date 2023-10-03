package com.devgeekssolutions.featuretipsdialogandselectbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomTipsDialog(onConfirm: (Int) -> Unit) {
    Dialog(onDismissRequest = { }) {
        var numPadValue by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier.width(359.dp).height(592.dp)
                .background(Color.White).padding(48.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(id = R.string.order_screen_tips_dialog_how_much),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF1C1C1E),
                    textAlign = TextAlign.Start,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            NumPadInputText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = numPadValue,
                currency = "SAR",
            )
            Spacer(modifier = Modifier.height(24.dp))
            NumPad(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    numPadValue += it
                },
                onClearAll = {
                    numPadValue = ""
                },
                onClearCharacter = {
                    numPadValue = numPadValue.dropLast(1)
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            NumPadConfirmButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.order_screen_tips_dialog_confirm),
                onClick = {
                    onConfirm(numPadValue.toInt())
                },
            )
        }
    }
}

@Composable
fun NumPadConfirmButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .background(color = Color(0xFF440099), shape = RoundedCornerShape(size = 8.dp))
            .width(263.dp)
            .height(48.dp),

    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            ),
        )
    }
}

@Composable
fun NumPadInputText(
    modifier: Modifier = Modifier,
    text: String,
    currency: String,
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF1C1C1E),
            ),
        ) {
            append(text)
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF1C1C1E),
            ),
        ) {
            append(currency)
        }
    }
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFAEAEB2),
                shape = RoundedCornerShape(size = 8.dp),
            )
            .padding(0.5.dp)
            .width(263.dp)
            .height(48.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
    ) {
        Text(text = annotatedString)
    }
}

@Composable
fun NumPadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = Color(0xFFAEAEB2),
                shape = RoundedCornerShape(size = 100.dp),
            )
            .size(60.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 100.dp)),
        shape = RoundedCornerShape(100.dp),
    ) {
        content()
    }
}

@Composable
fun NumberPadButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    NumPadButton(onClick = onClick, modifier = modifier) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF1C1C1E),
                textAlign = TextAlign.Center,
            ),
        )
    }
}

@Composable
fun BackSpacePadButton(
    modifier: Modifier = Modifier,
    onClearCharacter: () -> Unit,
) {
    NumPadButton(onClick = onClearCharacter, modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.backspace),
            contentDescription = null,
            tint = Color.Black,
        )
    }
}

@Composable
fun ClearAllPadButton(
    modifier: Modifier = Modifier,
    onClearAll: () -> Unit,
) {
    NumPadButton(onClick = onClearAll, modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.close),
            contentDescription = null,
            tint = Color.Black,
        )
    }
}

@Composable
fun NumPad(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onClearAll: () -> Unit,
    onClearCharacter: () -> Unit,
) {
    // Define the content for each button in the numpad
    val keys = rememberSaveable {
        listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("", "0", ""),
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        keys.forEachIndexed { rowIndex, row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                row.forEachIndexed { colIndex, key ->
                    if (rowIndex == 3) {
                        when (colIndex) {
                            0 -> ClearAllPadButton(onClearAll = onClearAll)
                            1 -> NumberPadButton(text = key, onClick = { onClick(key) })
                            2 -> BackSpacePadButton(onClearCharacter = onClearCharacter)
                        }
                    } else {
                        NumberPadButton(
                            text = key,
                            onClick = { onClick(key) },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TipsDialog() {
    CustomTipsDialog(onConfirm = {})
}
