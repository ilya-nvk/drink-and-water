package com.ilyanvk.drinkwater.presentation.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.Plant
import com.ilyanvk.drinkwater.presentation.gallery.components.GrownPlantComponent
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier, viewModel: GalleryViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var showDeleteDialog by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var plantToDelete: Plant? = null

    val singleTapMessage = stringResource(R.string.long_tap_to_delete_plant)
    val plantDeletedMessage = stringResource(R.string.plant_deleted)
    val restoreMessage = stringResource(R.string.restore)

    if (showDeleteDialog) {
        AlertDialog(onDismissRequest = { showDeleteDialog = false },
            confirmButton = {
                Button(onClick = {
                    plantToDelete?.let {
                        viewModel.onEvent(GalleryScreenEvent.DeletePlant(it))
                    }
                    showDeleteDialog = false
                    coroutineScope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = plantDeletedMessage,
                            actionLabel = restoreMessage,
                            duration = SnackbarDuration.Long
                        )
                        if (snackbarResult == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(GalleryScreenEvent.RestorePlant)
                        }
                    }
                }) {
                    Text(text = stringResource(id = R.string.delete))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            title = { Text(text = stringResource(R.string.delete_plant)) },
            text = { Text(text = stringResource(R.string.no_restore_warning)) },
            icon = { Icon(imageVector = Icons.Outlined.Delete, contentDescription = null) })
    }
    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        LargeTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.gallery)
                )
            }, scrollBehavior = scrollBehavior
        )
    }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Your plants",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = modifier.height(8.dp))
            }
            items(viewModel.plants.value) { plant ->
                GrownPlantComponent(modifier = Modifier
                    .combinedClickable(onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = singleTapMessage)
                        }
                    }, onLongClick = {
                        plantToDelete = plant
                        showDeleteDialog = true
                    }, onLongClickLabel = stringResource(id = R.string.delete)
                    )
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                    name = stringResource(id = plant.nameStringId),
                    painter = painterResource(
                        id = plant.pictureId4
                    ),
                    waterIntake = plant.totalWater
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}