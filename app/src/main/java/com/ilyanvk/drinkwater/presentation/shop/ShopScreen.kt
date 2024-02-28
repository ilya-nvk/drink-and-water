package com.ilyanvk.drinkwater.presentation.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.presentation.shop.components.PlantCard

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(
    modifier: Modifier = Modifier, viewModel: ShopViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        LargeTopAppBar(
            title = { Text(text = stringResource(id = R.string.shop)) },
            actions = {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = viewModel.coins.value.toString()
                    )
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_monetization_on_24),
                            contentDescription = stringResource(
                                R.string.coins
                            )
                        )
                    }
                }
            },
            scrollBehavior = scrollBehavior
        )
    }) { paddingValues ->
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(130.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(viewModel.plants.value) { plant ->
                PlantCard(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
                        .fillMaxWidth()
                        .clickable { },
                    name = stringResource(id = plant.nameStringId),
                    painter = painterResource(
                        id = plant.pictureId4
                    ),
                    price = plant.price,
                    waterIntake = plant.totalWater
                ) {

                }
            }
        }
    }
}