package com.ilyanvk.drinkwater.presentation.tracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.DrinkType
import com.ilyanvk.drinkwater.presentation.util.toStringRes
import com.ilyanvk.drinkwater.ui.theme.DrinkWaterTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRecordDialog(
    onConfirm: (DrinkType, Int) -> Unit, onDismiss: () -> Unit
) {
    var selectedDrinkType by remember { mutableStateOf(DrinkType.WATER) }
    var selectedAmount by remember { mutableIntStateOf(250) }

    BasicAlertDialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier.background(
                shape = MaterialTheme.shapes.extraLarge, color = MaterialTheme.colorScheme.surface
            ),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.outline_water_drop_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.new_record),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (selectedDrinkType != DrinkType.WATER) stringResource(
                            R.string.water_equivalent_text,
                            selectedAmount,
                            stringResource(id = selectedDrinkType.toStringRes()).lowercase(),
                            (selectedAmount * selectedDrinkType.coefficient).toInt()
                        ) else stringResource(R.string.ml_of_water, selectedAmount),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Column {
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    LazyColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        items(DrinkType.entries) { drinkType ->
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(selected = drinkType == selectedDrinkType,
                                    onClick = { selectedDrinkType = drinkType })
                                Text(
                                    text = stringResource(id = drinkType.toStringRes()),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Slider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        value = selectedAmount.toFloat(),
                        onValueChange = { selectedAmount = it.roundToInt() },
                        steps = 17,
                        valueRange = 100f..1000f
                    )
                }
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(onClick = { onDismiss() }) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Button(onClick = { onConfirm(selectedDrinkType, selectedAmount) }) {
                            Text(text = stringResource(id = R.string.save))
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun NewRecordDialogPreview() {
    DrinkWaterTheme {
        NewRecordDialog(onConfirm = { _, _ -> }, onDismiss = { })
    }
}
