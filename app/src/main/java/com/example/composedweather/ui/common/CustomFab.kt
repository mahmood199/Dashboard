package com.example.composedweather.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.ElectricBlue

@Composable
fun CustomFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
            defaultElevation = 12.dp,
            pressedElevation = 3.dp,
            focusedElevation = 8.dp,
            hoveredElevation = 6.dp,
        ),
        containerColor = ElectricBlue,
        onClick = {},
        modifier = modifier
            .clip(CircleShape)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        ElectricBlue.copy(alpha = 0.0625f),
                        ElectricBlue.copy(alpha = 0.0625f),
                    )
                )
            )
            .clip(CircleShape)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun CustomFabPreview() {
    ComposedWeatherTheme {
        CustomFab(
            onClick = {
            }
        )
    }
}
