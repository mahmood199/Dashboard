package com.example.composedweather.ui.feature.dashboard

data class DashboardViewState(
    val isLoading: Boolean,
    val dateRange: String,
    val contactNumber: String,
    val totalClicksForToday: Int,
) {
    companion object {
        fun default(): DashboardViewState {
            return DashboardViewState(
                isLoading = false,
                contactNumber = "+91 7980638965",
                dateRange = "Error",
                totalClicksForToday = 0
            )
        }
    }
}