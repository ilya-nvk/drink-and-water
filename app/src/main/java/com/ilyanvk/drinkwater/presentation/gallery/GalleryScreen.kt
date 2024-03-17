package com.ilyanvk.drinkwater.presentation.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.presentation.gallery.components.DeletePlantDialog
import com.ilyanvk.drinkwater.presentation.gallery.components.GrownPlantComponent
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier, viewModel: GalleryViewModel = hiltViewModel()
) {
    viewModel.updateData()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val singleTapMessage = stringResource(R.string.long_tap_to_delete_plant)
    val plantDeletedMessage = stringResource(R.string.plant_deleted)
    val restoreMessage = stringResource(R.string.restore)

    viewModel.state.value.deletePlantDialog?.let {
        DeletePlantDialog(
            onDismiss = { viewModel.onEvent(GalleryScreenEvent.HideDeletePlantDialog) },
            onConfirm = {
                viewModel.onEvent(GalleryScreenEvent.DeletePlant(it))
                viewModel.onEvent(GalleryScreenEvent.HideDeletePlantDialog)
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
            }
        )
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
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Your plants",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = modifier.height(8.dp))
            }
            items(viewModel.state.value.plants) { plant ->
                GrownPlantComponent(modifier = Modifier
                    .combinedClickable(onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = singleTapMessage)
                        }
                    }, onLongClick = {
                        viewModel.onEvent(GalleryScreenEvent.ShowDeletePlantDialog(plant))
                    }, onLongClickLabel = stringResource(id = R.string.delete)
                    )
                    .fillMaxWidth(),
                    name = stringResource(id = plant.nameStringId),
                    painter = painterResource(
                        id = plant.pictureId4
                    ),
                    waterIntake = plant.totalWater
                )
            }
        }
    }
}
