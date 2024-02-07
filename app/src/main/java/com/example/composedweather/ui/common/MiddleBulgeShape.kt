package com.example.composedweather.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.composedweather.ui.feature.home_container.BottomBarItem
import com.example.composedweather.ui.feature.home_container.BottomNavScreen
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.ElectricBlue

class MiddleBulgeShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width = size.width
        val height = size.height

        return Outline.Generic(
            path = drawMiddleBulgeShape(width, height)
        )
    }

    private fun drawMiddleBulgeShape(width: Float, height: Float): Path {
        val topHeight = 35f
        val circleRadiusInPx = 25f * 2
        return Path().apply {
            reset()

            moveTo(0f, topHeight)

            lineTo(width * 0.0f, topHeight)

            lineTo(width * 0.45f, topHeight)


            arcTo(
                rect = Rect(
                    left = width * 0.45f - circleRadiusInPx,
                    top = 0f,
                    right = width * 0.55f + circleRadiusInPx,
                    bottom = circleRadiusInPx * 2
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )

            lineTo(width * 0.55f, topHeight)

            lineTo(width * 1.0f, topHeight)

            lineTo(width * 1.0f, height * 1.0f)

            lineTo(width * 0.0f, height * 1.0f)


            close()
        }
    }
}

@Preview
@Composable
fun MiddleBulgeShapePreview() {
    ComposedWeatherTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .align(Alignment.BottomCenter)
            ) {
                val bottomBarItem = remember {
                    buildList {
                        add(BottomNavScreen.Links)
                        add(BottomNavScreen.Courses)
                        add(BottomNavScreen.Links)
                        add(BottomNavScreen.Campaigns)
                        add(BottomNavScreen.Profile)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MiddleBulgeShape())
                        .background(Color.White)
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    bottomBarItem.forEachIndexed { index, screens ->
                        when (index) {
                            2 -> {
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
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(
                                            brush = Brush.radialGradient(
                                                colors = listOf(
                                                    ElectricBlue,
                                                    ElectricBlue.copy(alpha = 0.0625f),
                                                )
                                            )
                                        )
                                        .padding(8.dp)
                                        .clip(CircleShape)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null
                                    )
                                }
                            }

                            else -> {
                                BottomBarItem(
                                    screen = screens,
                                    isSelected = index % 2 == 0,
                                    onClick = {
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}