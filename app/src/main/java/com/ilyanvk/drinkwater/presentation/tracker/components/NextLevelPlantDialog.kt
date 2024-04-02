package com.ilyanvk.drinkwater.presentation.tracker.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ilyanvk.drinkwater.R

@Composable
fun NextLevelPlantDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        Button(onClick = { onConfirm() }) {
            Text(text = stringResource(R.string.ok))
        }
    }, title = {
        Text(text = stringResource(R.string.your_plant_reached_next_level))
    }, text = {
        Text(text = stringResource(R.string.keep_going))
    }, icon = {
        Icon(
            painter = painterResource(id = R.drawable.outline_local_florist_24),
            contentDescription = null
        )
    })
}
