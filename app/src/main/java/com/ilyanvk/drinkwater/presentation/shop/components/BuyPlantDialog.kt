package com.ilyanvk.drinkwater.presentation.shop.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ilyanvk.drinkwater.R

@Composable
fun BuyPlantDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit, plantName: String, price: Int
) {
    AlertDialog(onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text(text = stringResource(R.string.buy))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        title = { Text(text = stringResource(R.string.buy_plant_question)) },
        text = { Text(text = stringResource(R.string.costs_coins, plantName, price)) },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.outline_local_florist_24),
                contentDescription = null
            )
        })
}