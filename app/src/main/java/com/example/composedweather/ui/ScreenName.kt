package com.example.composedweather.ui


object ScreenName {
    const val HOME = "home"
    const val DETAIL = "detail"
    const val SPLASH = "splash"
    const val DASHBOARD = "dashboard"
    const val HOME_WITH_BOTTOM_BAR = "home_with_bottom_bar"
}


sealed class Screen(val name: String) {
    data object Home : Screen(name = ScreenName.HOME)
    data object Detail : Screen(name = ScreenName.DETAIL)
    data object Splash : Screen(name = ScreenName.SPLASH)
    data object Dashboard : Screen(name = ScreenName.DASHBOARD)
    data object HomeWithBottomBar : Screen(name = ScreenName.HOME_WITH_BOTTOM_BAR)
}