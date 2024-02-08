package com.example.composedweather.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedweather.R
import com.example.composedweather.ui.feature.home_container.BottomBarItem
import com.example.composedweather.ui.feature.home_container.BottomNavScreen
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.SilverChalice

@Composable
fun CustomBottomNavigation(
    currentSelectedTab: Int,
    bottomBarItems: List<BottomNavScreen>,
    onTabClicked: (Int) -> Unit,
    onFabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy((-0.5).dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_bottom_nav_middle_bulge),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                bottomBarItems.forEachIndexed { index, screens ->
                    when (index) {
                        2 -> {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                            )
                        }

                        else -> {
                            BottomBarItem(
                                screen = screens,
                                isSelected = index == currentSelectedTab,
                                onClick = {
                                    onTabClicked(it)
                                },
                                modifier = Modifier
                                    .weight(1f)
                            )

                        }
                    }
                }
            }
        }

        CustomFab(
            onClick = onFabClicked,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
        )

    }
}


@Preview
@Composable
fun CustomBottomNavigationPreview() {
    ComposedWeatherTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SilverChalice)
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

            CustomBottomNavigation(
                currentSelectedTab = 0,
                bottomBarItems = bottomBarItem,
                onTabClicked = {},
                onFabClicked = {},
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}