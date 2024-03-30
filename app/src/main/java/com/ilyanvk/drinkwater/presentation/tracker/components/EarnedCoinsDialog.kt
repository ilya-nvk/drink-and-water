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
fun EarnedCoinsDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit, coins: Int
) {
    AlertDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        Button(onClick = { onDismiss() }) {
            Text(text = stringResource(id = R.string.ok))
        }
    }, title = {
        Text(text = stringResource(R.string.coins_you_earned, coins))
    }, icon = {
        Icon(
            painter = painterResource(id = R.drawable.outline_monetization_on_24),
            contentDescription = null
        )
    })
}
