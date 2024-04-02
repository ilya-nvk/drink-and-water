package com.ilyanvk.drinkwater.presentation.tracker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import com.ilyanvk.drinkwater.presentation.tracker.components.DeleteRecordDialog
import com.ilyanvk.drinkwater.presentation.tracker.components.EarnedCoinsDialog
import com.ilyanvk.drinkwater.presentation.tracker.components.NewRecordDialog
import com.ilyanvk.drinkwater.presentation.tracker.components.NextLevelPlantDialog
import com.ilyanvk.drinkwater.presentation.tracker.components.PlantGrownDialog
import com.ilyanvk.drinkwater.presentation.tracker.components.ProgressComponent
import com.ilyanvk.drinkwater.presentation.tracker.components.RecordComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun TrackerScreen(
    modifier: Modifier = Modifier, viewModel: TrackerViewModel = hiltViewModel()
) {
    viewModel.updateGrowingPlant()
    val state = viewModel.state.value

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val singleTapMessage = stringResource(R.string.long_tap_to_delete_record)
    val plantDeletedMessage = stringResource(R.string.record_deleted)
    val noCurrentPlantError = stringResource(R.string.you_need_to_buy_a_new_plant_to_continue)

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    var showNewRecordDialog by remember { mutableStateOf(false) }
    if (showNewRecordDialog) {
        NewRecordDialog(onConfirm = { drinkType, amount ->
            viewModel.onEvent(TrackerScreenEvent.NewRecord(drinkType, amount))
            showNewRecordDialog = false
        }, onDismiss = { showNewRecordDialog = false })
    }


    var showDeleteRecordDialog by remember { mutableStateOf(false) }
    var recordToDelete: IntakeRecord? by remember { mutableStateOf(null) }
    if (showDeleteRecordDialog) {
        recordToDelete?.let {
            DeleteRecordDialog(onDismiss = { showDeleteRecordDialog = false }) {
                viewModel.onEvent(TrackerScreenEvent.DeleteRecord(it))
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = plantDeletedMessage)
                }
                showDeleteRecordDialog = false

            }
        }
    }

    if (state.coinsEarned != null) {
        EarnedCoinsDialog(
            onDismiss = { viewModel.onEvent(TrackerScreenEvent.HideEarnedCoinsDialog) },
            onConfirm = { viewModel.onEvent(TrackerScreenEvent.HideEarnedCoinsDialog) },
            coins = state.coinsEarned
        )
    }

    if (state.showPlantIsGrownDialog) {
        PlantGrownDialog(
            onDismiss = { viewModel.onEvent(TrackerScreenEvent.HidePlantGrownDialog) },
            onConfirm = { viewModel.onEvent(TrackerScreenEvent.HidePlantGrownDialog) }
        )
    }

    if (state.showNextLevelPlantDialog) {
        NextLevelPlantDialog(
            onDismiss = { viewModel.onEvent(TrackerScreenEvent.HideNextLevelPlantDialog) },
            onConfirm = { viewModel.onEvent(TrackerScreenEvent.HideNextLevelPlantDialog) }
        )
    }

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        LargeTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.tracker))
            }, scrollBehavior = scrollBehavior
        )
    }, floatingActionButton = {
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.new_record)) },
            icon = { Icon(imageVector = Icons.Outlined.Add, contentDescription = null) },
            onClick = {
                if (state.plant != null) {
                    showNewRecordDialog = true
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message = noCurrentPlantError)
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.today),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    if (state.plant != null) {
                        Image(
                            painter = painterResource(id = state.plant.getCurrentLevelPictureId()),
                            contentDescription = stringResource(
                                id = state.plant.nameStringId
                            )
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.you_need_to_buy_a_plant),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
                if (state.plant != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    ProgressComponent(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        text = stringResource(
                            R.string.ml_until_next_level, state.intakeGrowGoal - state.intakeGrow
                        ),
                        progress = state.intakeGrow.toFloat() / state.intakeGrowGoal
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                ProgressComponent(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    text = stringResource(
                        R.string.ml_left_today, state.intakeTodayGoal - state.intakeToday
                    ),
                    progress = state.intakeToday.toFloat() / state.intakeTodayGoal
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.today_s_records),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.records) { record ->
                RecordComponent(record = record,
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(message = singleTapMessage)
                            }
                        }, onLongClick = {
                            recordToDelete = record
                            showDeleteRecordDialog = true
                        }, onLongClickLabel = stringResource(id = R.string.delete)
                        )
                )
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
