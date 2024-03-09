package com.ilyanvk.drinkwater.presentation.tracker.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ilyanvk.drinkwater.R

@Composable
fun DeleteRecordDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        Button(onClick = { onConfirm() }) {
            Text(text = stringResource(R.string.delete))
        }
    }, dismissButton = {
        TextButton(onClick = { onDismiss() }) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }, title = {
        Text(text = stringResource(id = R.string.delete_record_question))
    }, text = {
        Text(text = stringResource(id = R.string.no_restore_warning))
    }, icon = {
        Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
    })
}