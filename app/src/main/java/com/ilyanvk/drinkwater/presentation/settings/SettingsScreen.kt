package com.ilyanvk.drinkwater.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.Theme
import com.ilyanvk.drinkwater.presentation.settings.components.TimePickerDialog
import com.ilyanvk.drinkwater.presentation.util.convertMillisecondsToTimestamp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier, viewModel: SettingsViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                Button(onClick = {
                    viewModel.onEvent(
                        SettingsEvent.AddNotification(
                            (timePickerState.hour * 60 * 60 * 1000 + timePickerState.minute * 60 * 1000).toLong()
                        )
                    )
                    showTimePicker = false
                }) {
                    Text(text = stringResource(id = R.string.add))
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            },
        ) {
            TimePicker(state = timePickerState)
        }
    }

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        LargeTopAppBar(
            title = { Text(text = stringResource(id = R.string.settings)) },
            scrollBehavior = scrollBehavior
        )
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(paddingValues)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.theme),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                MultiChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                ) {
                    SegmentedButton(
                        checked = viewModel.state.value.theme == Theme.DARK, onCheckedChange = {
                            if (it) {
                                viewModel.onEvent(SettingsEvent.SetTheme(Theme.DARK))
                            }
                        }, shape = SegmentedButtonDefaults.baseShape.copy(
                            topEnd = CornerSize(0.dp), bottomEnd = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.theme_dark))
                    }
                    SegmentedButton(
                        checked = viewModel.state.value.theme == Theme.SYSTEM, onCheckedChange = {
                            if (it) {
                                viewModel.onEvent(SettingsEvent.SetTheme(Theme.SYSTEM))
                            }
                        }, shape = SegmentedButtonDefaults.baseShape.copy(all = CornerSize(0.dp))
                    ) {
                        Text(text = stringResource(id = R.string.theme_system))
                    }
                    SegmentedButton(
                        checked = viewModel.state.value.theme == Theme.LIGHT, onCheckedChange = {
                            if (it) {
                                viewModel.onEvent(SettingsEvent.SetTheme(Theme.LIGHT))
                            }
                        }, shape = SegmentedButtonDefaults.baseShape.copy(
                            topStart = CornerSize(0.dp), bottomStart = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.theme_light))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.notifications),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(viewModel.state.value.notifications) { notification ->
                ListItem(modifier = Modifier.fillMaxWidth(), leadingContent = {
                    Checkbox(checked = notification.isActive, onCheckedChange = {
                        viewModel.onEvent(
                            SettingsEvent.UpdateNotification(
                                notification.copy(
                                    isActive = it
                                )
                            )
                        )
                    })
                }, headlineContent = {
                    Text(
                        text = convertMillisecondsToTimestamp(
                            notification.time
                        )
                    )
                }, trailingContent = {
                    IconButton(onClick = {
                        viewModel.onEvent(
                            SettingsEvent.RemoveNotification(
                                notification
                            )
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete)
                        )
                    }
                })
            }
            item {
                ListItem(modifier = Modifier.clickable {
                    showTimePicker = true
                },
                    headlineContent = { Text(text = stringResource(R.string.add_notification)) },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.Add, contentDescription = null
                        )
                    })
            }
        }
    }
}