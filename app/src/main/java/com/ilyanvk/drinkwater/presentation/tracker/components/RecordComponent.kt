package com.ilyanvk.drinkwater.presentation.tracker.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.DrinkType
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun RecordComponent(
    modifier: Modifier = Modifier, record: IntakeRecord
) {
    Column(
        modifier = modifier
    ) {
        val drinkTypeString = when (record.drinkType) {
            DrinkType.WATER -> stringResource(R.string.water)
            DrinkType.TEA -> stringResource(R.string.tea)
            DrinkType.COFFEE -> stringResource(R.string.coffee)
            DrinkType.SODA -> stringResource(R.string.soda)
            DrinkType.JUICE -> stringResource(R.string.juice)
            DrinkType.MILK -> stringResource(R.string.milk)
            DrinkType.ALCOHOL -> stringResource(R.string.alcohol)
        }
        val timeString = convertMillisecondsToTimestamp(record.time)
        Text(
            text = stringResource(
                R.string.record_header, drinkTypeString, record.intakeMilliliters
            ), style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = timeString, style = MaterialTheme.typography.bodySmall
        )
    }
}

private fun convertMillisecondsToTimestamp(milliseconds: Long): String {
    val sdf = SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
    val date = Date(milliseconds)
    return sdf.format(date)
}