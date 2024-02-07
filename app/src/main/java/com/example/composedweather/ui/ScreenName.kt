package com.example.composedweather.ui


object ScreenName {
    const val HOME_WITH_BOTTOM_BAR = "home_with_bottom_bar"
}


sealed class Screen(val name: String) {
    data object HomeWithBottomBar : Screen(name = ScreenName.HOME_WITH_BOTTOM_BAR)
}