package com.ilyanvk.drinkwater.presentation.profile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ilyanvk.drinkwater.R

@Composable
fun ResetProgressDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text(text = stringResource(id = R.string.reset))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        title = { Text(text = stringResource(R.string.reset_progress_question)) },
        text = { Text(text = stringResource(R.string.restore_progress_warning)) },
        icon = { Icon(imageVector = Icons.Outlined.Refresh, contentDescription = null) })
}
