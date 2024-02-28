package com.ilyanvk.drinkwater.presentation.profile

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.Sex
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier, viewModel: ProfileViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        LargeTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.profile),
                    style = MaterialTheme.typography.displayMedium
                )
            }, scrollBehavior = scrollBehavior
        )
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    value = viewModel.state.value.name,
                    label = { Text(text = stringResource(R.string.name)) },
                    onValueChange = { viewModel.onEvent(ProfileScreenEvent.ChangeName(it)) },
                    singleLine = true,
                    isError = !viewModel.state.value.isNameCorrect()
                )
                Spacer(modifier = Modifier.height(8.dp))
                MultiChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    SegmentedButton(
                        checked = viewModel.state.value.sex == Sex.MALE,
                        onCheckedChange = {
                            if (it) viewModel.onEvent(
                                ProfileScreenEvent.ChangeSex(
                                    Sex.MALE
                                )
                            )
                        },
                        shape = SegmentedButtonDefaults.baseShape.copy(
                            topEnd = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(R.string.male))
                    }
                    SegmentedButton(
                        checked = viewModel.state.value.sex == Sex.FEMALE,
                        onCheckedChange = {
                            if (it) viewModel.onEvent(
                                ProfileScreenEvent.ChangeSex(
                                    Sex.FEMALE
                                )
                            )
                        },
                        shape = SegmentedButtonDefaults.baseShape.copy(
                            topStart = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp)
                        )
                    ) {
                        Text(text = stringResource(R.string.female))
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
                        onValueChange = { viewModel.onEvent(ProfileScreenEvent.ChangeWeight(it)) },
                        singleLine = true,
                        isError = !viewModel.state.value.isWeightCorrect(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.state.value.height,
                        label = { Text(text = stringResource(R.string.height)) },
                        onValueChange = { viewModel.onEvent(ProfileScreenEvent.ChangeHeight(it)) },
                        singleLine = true,
                        isError = !viewModel.state.value.isHeightCorrect(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    value = convertMillisecondsToDate(viewModel.state.value.dateOfBirth),
                    label = { Text(text = stringResource(R.string.birth_date)) },
                    onValueChange = { },
                    singleLine = true,
                    isError = !viewModel.state.value.isDateOfBirthCorrect()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text = stringResource(R.string.reset_progress))
                    }
                    Button(onClick = { /*TODO*/ }) {
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