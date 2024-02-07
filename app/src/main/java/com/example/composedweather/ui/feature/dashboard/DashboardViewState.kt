package com.example.composedweather.ui.feature.dashboard

data class DashboardViewState(
    val greetingMessage: String,
    val isLoading: Boolean,
    val dateRange: String,
    val contactNumber: String,
    val totalClicksForToday: Int,
) {
    companion object {
        fun default(): DashboardViewState {
            return DashboardViewState(
                greetingMessage = "Good Morning",
                isLoading = true,
                contactNumber = "+91 7980638965",
                dateRange = "Error",
                totalClicksForToday = 0
            )
        }
    }
}