package com.example.domain.dashboard

import com.example.core_network.NetworkResult
import com.example.data.model.response.DashboardResponse
import com.example.data.repositories.contract.DashboardRepository
import com.example.data.repositories.contract.UserPreferenceRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DashboardDataUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {

    suspend fun getDashboard(): NetworkResult<DashboardResponse> {
        val authToken = userPreferenceRepository.getUserPreferences().first().bearerToken
        return dashboardRepository.getDashboard(authToken)
    }

}