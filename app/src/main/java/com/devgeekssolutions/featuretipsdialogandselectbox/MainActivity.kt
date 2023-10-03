package com.devgeekssolutions.featuretipsdialogandselectbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devgeekssolutions.featuretipsdialogandselectbox.ui.theme.FeatureTipsDialogAndSelectBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeatureTipsDialogAndSelectBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        val tips = remember {
                            mutableStateListOf(
                                Tip(0, 0, true),
                                Tip(1, 0, false),
                                Tip(2, 0, false),
                                Tip(3, 0, false),
                                Tip(4, 0, false, isCustom = true),
                            )
                        }
                        TipSection(
                            tips = tips,
                            onTipSelected = { selectedTip ->
                                tips.replaceAll { tip ->
                                    if (tip.id == selectedTip.id) {
                                        tip.copy(isSelected = true)
                                    } else {
                                        tip.copy(isSelected = false)
                                    }
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TipSection(modifier: Modifier = Modifier, tips: List<Tip>, onTipSelected: (Tip) -> Unit) {
    Box(
        modifier = modifier.width(389.dp)
            .height(178.dp)
            .background(color = Color(0xFF3A3A3C))
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 24.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Add a tip for the restaurant. \uD83D\uDE04",
            )
            Text(text = "100% of your tip goes to the waiters.")
            Spacer(modifier = Modifier.height(24.dp))
            TipSelectionRow(
                tips = tips,
                onTipSelected = onTipSelected,
            )
        }
    }
}

@Composable
fun TipSelectionSlot(
    modifier: Modifier = Modifier,
    tip: Tip,
    onSelected: (Tip) -> Unit = {},
) {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.border(
            width = 2.dp,
            color = if (tip.isSelected) Color(0xFF7457C5) else Color.White,
            shape = RoundedCornerShape(size = 8.dp),
        )
            .width(61.8.dp)
            .height(54.dp)
            .clickable {
                if (tip.isCustom) {
                    // show dialog here and onDismiss call onSelected
                    showDialog = true
                } else {
                    onSelected(tip)
                }
            }
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .padding(12.dp),
    ) {
        if(showDialog){
            CustomTipsDialog(
                onConfirm = {
                    showDialog = false
                    onSelected(tip)
                },
            )
        }
    }
}

@Composable
fun TipSelectionRow(
    modifier: Modifier = Modifier,
    tips: List<Tip>,
    onTipSelected: (Tip) -> Unit,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        tips.forEach { tip ->
            TipSelectionSlot(tip = tip, onSelected = onTipSelected)
        }
    }
}

@Preview
@Composable
fun TipSlotPrev() {
    TipSelectionSlot(tip = Tip(0, 0, false))
}

@Preview()
@Composable
fun TipSlotSelectedPrev() {
    TipSelectionSlot(tip = Tip(0, 0, true))
}

@Preview
@Composable
fun TipRowPrev() {
    TipSelectionRow(
        tips = listOf(
            Tip(0, 0, true),
            Tip(1, 0, false),
            Tip(2, 0, false),
            Tip(3, 0, false, isCustom = true),
        ),
        onTipSelected = {},
    )
}
