package com.ilyanvk.drinkwater.presentation.profile.components

import android.os.Build
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilyanvk.drinkwater.R
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateOfBirthDialog(
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit,
    initialDisplayDateMilliseconds: Long? = null
) {
    val state = rememberDatePickerState(initialSelectedDateMillis = initialDisplayDateMilliseconds,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val yearInMilliseconds = 31536000000
                return (System.currentTimeMillis() - utcTimeMillis) / yearInMilliseconds in 6..150
            }

            override fun isSelectableYear(year: Int): Boolean {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalDate.now().year - year in 6..150
                } else {
                    true
                }
            }
        })
    DatePickerDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        Button(
            onClick = { state.selectedDateMillis?.let { onDateSelected(it) } },
            enabled = state.selectedDateMillis != null
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }, dismissButton = {
        TextButton(onClick = { onDismiss() }) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }) {
        DatePicker(state = state, title = {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(R.string.select_birth_date)
            )
        })
    }
}