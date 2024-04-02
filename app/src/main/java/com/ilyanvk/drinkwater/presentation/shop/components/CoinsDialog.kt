package com.ilyanvk.drinkwater.presentation.shop.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ilyanvk.drinkwater.R

@Composable
fun CoinsDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit, coins: Int
) {
    AlertDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        Button(onClick = { onDismiss() }) {
            Text(text = stringResource(id = R.string.ok))
        }
    }, title = {
        Text(
            text = stringResource(
                R.string.you_have_coins, coins
            )
        )
    }, text = { Text(text = stringResource(id = R.string.coins_description)) }, icon = {
        Icon(
            painter = painterResource(id = R.drawable.outline_monetization_on_24),
            contentDescription = null
        )
    })
}