package com.example.composedweather.ui.feature.home_container

data class HomeContainerViewState(
    val isLoading: Boolean,
    val currentPage: Int
) {

    companion object {
        fun default(): HomeContainerViewState {
            return HomeContainerViewState(isLoading = false, currentPage = 0)
        }
    }

}