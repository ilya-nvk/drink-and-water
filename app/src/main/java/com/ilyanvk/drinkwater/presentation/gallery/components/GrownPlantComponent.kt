package com.ilyanvk.drinkwater.presentation.gallery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyanvk.drinkwater.R

@Composable
fun GrownPlantComponent(
    modifier: Modifier = Modifier,
    name: String,
    painter: Painter,
    waterIntake: Int
) {
    ListItem(
        modifier = modifier,
        headlineContent = { Text(text = name) },
        supportingContent = {
            Text(text = stringResource(R.string.ml, waterIntake))
        },
        leadingContent = {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painter,
                contentDescription = null
            )
        },
    )
}

@Preview
@Composable
fun GrownPlantComponentPreview() {
    GrownPlantComponent(
        name = "Rose", painter = painterResource(id = R.drawable.plant_test), waterIntake = 1500
    )
}
