package com.example.composedweather.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedweather.ui.feature.home_container.HomeContainerUI
import com.example.composedweather.ui.theme.ComposedWeatherTheme

@Composable
fun CentralNavigation(
    backPress: () -> Unit,
    openWhatsApp: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeWithBottomBar.name,
        modifier = modifier
    ) {
        composable(route = Screen.HomeWithBottomBar.name) {
            HomeContainerUI(openWhatsApp = openWhatsApp)
        }
    }
}

@Preview
@Composable
fun CentralNavigationPreview() {
    ComposedWeatherTheme {
        CentralNavigation(
            backPress = {

            },
            openWhatsApp = {

            }
        )
    }
}