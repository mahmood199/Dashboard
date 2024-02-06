package com.example.composedweather.ui.feature.home_container

import com.example.composedweather.R

sealed class BottomNavScreen(
    val position: Int,
    val text: String,
    val drawable: Int
) {
    data object Links : BottomNavScreen(position = 0, text = "Links", R.drawable.ic_link_2)
    data object Courses : BottomNavScreen(position = 1, text = "Courses", R.drawable.ic_courses)
    data object Campaigns : BottomNavScreen(position = 2, text = "Campaigns", R.drawable.ic_campaigns)
    data object Profile : BottomNavScreen(position = 3, text = "Profile", R.drawable.ic_profile)
}