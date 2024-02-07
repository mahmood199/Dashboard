package com.example.composedweather.ui

import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedweather.ui.feature.dashboard.DashboardUIContainer
import com.example.composedweather.ui.feature.home.HomeUI
import com.example.composedweather.ui.feature.home_container.HomeContainerUI
import com.example.composedweather.ui.feature.search.DetailUI
import com.example.composedweather.ui.feature.splash.SplashUI
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
        startDestination = Screen.Splash.name,
        modifier = modifier
    ) {
        composable(route = Screen.Splash.name) {
            SplashUI(
                navigateToHome = {
                    navController.navigate(route = Screen.HomeWithBottomBar.name) {
                        popUpTo(Screen.Splash.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.Home.name,
            enterTransition = {
                fadeIn()
            }
        ) {
            HomeUI(
                onBackPressed = {
                    backPress()
                },
                navigateToSearch = {
                    navController.navigate(Screen.Detail.name)
                }
            )
        }

        composable(
            route = Screen.Detail.name,
            enterTransition = {
                fadeIn()
            }
        ) {
            DetailUI(onBackPressed = {
                navController.popBackStack()
            })
        }

        composable(route = Screen.Dashboard.name) {
            DashboardUIContainer(openWhatsApp = openWhatsApp)
        }

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