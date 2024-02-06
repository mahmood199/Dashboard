package com.example.composedweather.ui.feature.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedweather.util.formatDate
import com.example.core_network.NetworkResult
import com.example.data.model.response.DashboardResponse
import com.example.data.model.response.LinkData
import com.example.domain.dashboard.DashboardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardDataUseCase: DashboardDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardViewState.default())
    val state = _state.asStateFlow()

    private val stateLock = Mutex()

    val linkInfo = mutableStateListOf<Pair<String, List<LinkData>>>()


    init {
        fetchDashboard()
    }

    private fun fetchDashboard() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState {
                copy(isLoading = true)
            }
            when (val response: NetworkResult<DashboardResponse> =
                dashboardDataUseCase.getDashboard()) {
                is NetworkResult.Exception -> {
                    response.e.printStackTrace()
                }

                is NetworkResult.RedirectError -> {
                    response.e.printStackTrace()
                }

                is NetworkResult.ServerError -> {
                    response.e.printStackTrace()
                }

                is NetworkResult.UnAuthorised -> {
                    response.e.printStackTrace()
                }

                is NetworkResult.Success -> {
                    linkInfo.add(Pair("Top Links", response.data.data.topLinks))
                    linkInfo.add(Pair("Recent Links", response.data.data.recentLinks))
                    linkInfo.add(Pair("Favourite Links", response.data.data.favouriteLinks))

                    updateDateSafely(response.data.data.overallUrlChart)

                    updateState {
                        copy(
                            contactNumber = response.data.supportWhatsappNumber,
                            totalClicksForToday = response.data.totalClicks
                        )
                    }
                }
            }
            updateState {
                copy(isLoading = false)
            }
        }
    }

    private suspend fun updateDateSafely(overallUrlChart: Map<String, Int>) {
        Log.d("DashboardViewModel", overallUrlChart.toString())
        if (overallUrlChart.entries.isEmpty())
            return

        val startDate = overallUrlChart.entries.first()
        val endDate = overallUrlChart.entries.last()

        Log.d("DashboardViewModel", overallUrlChart.toString())

        val startDateToDisplay = formatDate(dateString = startDate.key)
        val endDateToDisplay = formatDate(dateString = endDate.key)

        updateState {
            copy(dateRange = "$startDateToDisplay - $endDateToDisplay")
        }
    }

    private suspend fun updateState(updater: DashboardViewState.() -> DashboardViewState) {
        stateLock.withLock {
            _state.value = _state.value.updater()
        }
    }
}