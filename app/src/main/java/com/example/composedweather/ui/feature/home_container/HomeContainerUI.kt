package com.example.composedweather.ui.feature.home_container

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedweather.ui.feature.dashboard.DashboardUIContainer
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.ElectricBlue
import com.example.composedweather.ui.theme.FigtreeSemiBold
import com.example.composedweather.ui.theme.SilverChalice

@Composable
fun HomeContainerUI(
    openWhatsApp: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeContainerViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        bottomBar = {
            val bottomBarItem = remember {
                buildList {
                    add(BottomNavScreen.Links)
                    add(BottomNavScreen.Courses)
                    add(BottomNavScreen.Links)
                    add(BottomNavScreen.Campaigns)
                    add(BottomNavScreen.Profile)
                }
            }

            NavBottomBar(
                state = state,
                bottomBarItem = bottomBarItem,
                onItemClicked = {
                    viewModel.updateSelectedScreen(it)
                }
            )

        },
        modifier = modifier
            .fillMaxSize()
    ) {
        DashboardUIContainer(
            openWhatsApp = openWhatsApp,
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding() - it.calculateBottomPadding())
        )
    }
}

@Composable
fun NavBottomBar(
    state: HomeContainerViewState,
    bottomBarItem: List<BottomNavScreen>,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                                isSelected = state.currentPage == index,
                                onClick = {
                                    onItemClicked(it)
                                },
                                modifier = Modifier
                                    .weight(1f)
                            )

                        }
                    }
                }
            }
        },
        floatingActionButton = {
        },
        containerColor = Color.White,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun BottomBarItem(
    screen: BottomNavScreen,
    isSelected: Boolean,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .clickable(onClick = {
                onClick(screen.position)
            })
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(screen.drawable),
            contentDescription = null,
            tint = if (isSelected) Color.Black else SilverChalice,
            modifier = Modifier.size(if (isSelected) 32.dp else 24.dp)
        )

        Text(
            text = screen.text,
            fontFamily = FigtreeSemiBold,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) Color.Black else SilverChalice,
        )
    }
}

@Preview
@Composable
fun HomeContainerUIPreview() {
    ComposedWeatherTheme {
        HomeContainerUI(
            openWhatsApp = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}