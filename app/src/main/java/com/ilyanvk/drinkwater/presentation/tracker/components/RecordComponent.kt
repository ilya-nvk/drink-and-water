package com.ilyanvk.drinkwater.presentation.tracker.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import com.ilyanvk.drinkwater.presentation.util.convertMillisecondsToTimestamp
import com.ilyanvk.drinkwater.presentation.util.toStringRes

@Composable
fun RecordComponent(
    modifier: Modifier = Modifier, record: IntakeRecord
) {
    val timeString = convertMillisecondsToTimestamp(record.time)

    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(
                R.string.record_header,
                stringResource(id = record.drinkType.toStringRes()),
                record.intakeMilliliters
            ), style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = timeString, style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
