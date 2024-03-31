package com.ilyanvk.drinkwater.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.util.ActivityLevel
import com.ilyanvk.drinkwater.presentation.profile.components.DateOfBirthDialog
import com.ilyanvk.drinkwater.presentation.profile.components.ResetProgressDialog
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier, viewModel: ProfileViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val saveErrorMessage = stringResource(R.string.save_profile_error)
    val saveSuccessMessage = stringResource(R.string.save_profile_success)
    val progressResetMessage = stringResource(R.string.progress_reset_success)

    if (viewModel.state.value.showDatePickerDialog) {
        DateOfBirthDialog(
            onDismiss = { viewModel.onEvent(ProfileScreenEvent.HideDatePickerDialog) },
            onDateSelected = {
                viewModel.onEvent(ProfileScreenEvent.UpdateDateOfBirth(it))
                viewModel.onEvent(ProfileScreenEvent.HideDatePickerDialog)
            },
            initialDisplayDateMilliseconds = viewModel.state.value.dateOfBirth
        )
    }

    var showResetProgressDialog by remember { mutableStateOf(false) }
    if (showResetProgressDialog) {
        ResetProgressDialog(
            onDismiss = { showResetProgressDialog = false },
            onConfirm = {
                viewModel.onEvent(ProfileScreenEvent.ResetProgress)
                showResetProgressDialog = false
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = progressResetMessage)
                }
            }
        )
    }

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        LargeTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.profile))
            }, scrollBehavior = scrollBehavior
        )
    }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    value = viewModel.state.value.name,
                    label = { Text(text = stringResource(R.string.name)) },
                    onValueChange = { viewModel.onEvent(ProfileScreenEvent.UpdateName(it)) },
                    singleLine = true,
                    isError = !viewModel.state.value.isNameCorrect()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.sex),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                MultiChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    SegmentedButton(
                        checked = viewModel.state.value.sex == Sex.MALE, onCheckedChange = {
                            if (it) viewModel.onEvent(
                                ProfileScreenEvent.UpdateSex(
                                    Sex.MALE
                                )
                            )
                        }, shape = SegmentedButtonDefaults.baseShape.copy(
                            topEnd = CornerSize(0.dp), bottomEnd = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(R.string.male))
                    }
                    SegmentedButton(
                        checked = viewModel.state.value.sex == Sex.FEMALE, onCheckedChange = {
                            if (it) viewModel.onEvent(
                                ProfileScreenEvent.UpdateSex(
                                    Sex.FEMALE
                                )
                            )
                        }, shape = SegmentedButtonDefaults.baseShape.copy(
                            topStart = CornerSize(0.dp), bottomStart = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(R.string.female))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.activity_level),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                MultiChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                ) {
                    SegmentedButton(
                        checked = viewModel.state.value.activityLevel == ActivityLevel.LOW,
                        onCheckedChange = {
                            if (it) {
                                viewModel.onEvent(
                                    ProfileScreenEvent.UpdateActivityLevel(
                                        ActivityLevel.LOW
                                    )
                                )
                            }
                        },
                        shape = SegmentedButtonDefaults.baseShape.copy(
                            topEnd = CornerSize(0.dp), bottomEnd = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.low))
                    }
                    SegmentedButton(
                        checked = viewModel.state.value.activityLevel == ActivityLevel.MEDIUM,
                        onCheckedChange = {
                            if (it) {
                                viewModel.onEvent(
                                    ProfileScreenEvent.UpdateActivityLevel(
                                        ActivityLevel.MEDIUM
                                    )
                                )
                            }
                        },
                        shape = SegmentedButtonDefaults.baseShape.copy(all = CornerSize(0.dp))
                    ) {
                        Text(text = stringResource(id = R.string.medium))
                    }
                    SegmentedButton(
                        checked = viewModel.state.value.activityLevel == ActivityLevel.HIGH,
                        onCheckedChange = {
                            if (it) {
                                viewModel.onEvent(
                                    ProfileScreenEvent.UpdateActivityLevel(
                                        ActivityLevel.HIGH
                                    )
                                )
                            }
                        },
                        shape = SegmentedButtonDefaults.baseShape.copy(
                            topStart = CornerSize(0.dp), bottomStart = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.high))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.state.value.weight,
                        label = { Text(text = stringResource(R.string.weight)) },
                        onValueChange = { viewModel.onEvent(ProfileScreenEvent.UpdateWeight(it)) },
                        singleLine = true,
                        isError = !viewModel.state.value.isWeightCorrect(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.state.value.height,
                        label = { Text(text = stringResource(R.string.height)) },
                        onValueChange = { viewModel.onEvent(ProfileScreenEvent.UpdateHeight(it)) },
                        singleLine = true,
                        isError = !viewModel.state.value.isHeightCorrect(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clickable { viewModel.onEvent(ProfileScreenEvent.ShowDatePickerDialog) }
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .focusProperties { canFocus = false }
                            .fillMaxWidth(),
                        value = convertMillisecondsToDate(viewModel.state.value.dateOfBirth),
                        label = { Text(text = stringResource(R.string.birth_date)) },
                        onValueChange = { },
                        singleLine = true,
                        isError = !viewModel.state.value.isDateOfBirthCorrect()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(onClick = { showResetProgressDialog = true }) {
                        Text(text = stringResource(R.string.reset_progress))
                    }
                    Button(onClick = {
                        viewModel.onEvent(ProfileScreenEvent.SaveProfile)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = if (viewModel.state.value.isProfileCorrect()) saveSuccessMessage else saveErrorMessage)
                        }
                    },
                        enabled = viewModel.state.value.isProfileCorrect()
                    ) {
                        Text(text = stringResource(R.string.save))
                    }
                }
            }
        }
    }
}

private fun convertMillisecondsToDate(milliseconds: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date = Date(milliseconds)
    return sdf.format(date)
}
