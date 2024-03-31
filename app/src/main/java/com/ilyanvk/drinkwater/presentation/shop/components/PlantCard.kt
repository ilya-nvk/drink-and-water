package com.ilyanvk.drinkwater.presentation.shop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.ui.theme.DrinkWaterTheme

@Composable
fun PlantCard(
    modifier: Modifier = Modifier,
    name: String,
    painter: Painter,
    price: Int,
    waterIntake: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.large)
            .clickable { onClick() }) {
        Column {
            Image(
                painter = painter,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(all = 8.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (price != 0) stringResource(
                            R.string.name_price,
                            name,
                            price
                        ) else stringResource(
                            R.string.free
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = stringResource(R.string.needs_ml_to_grow, waterIntake),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PlantCardPreview() {
    DrinkWaterTheme {
        PlantCard(
            name = "Geranium",
            painter = painterResource(id = R.drawable.plant_test),
            price = 8,
            waterIntake = 1500,
            modifier = Modifier.width(180.dp)
        ) {}
    }
}
