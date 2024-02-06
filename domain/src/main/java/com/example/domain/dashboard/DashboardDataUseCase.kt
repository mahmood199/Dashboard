package com.example.domain.dashboard

import com.example.core_network.NetworkResult
import com.example.data.model.response.DashboardResponse
import com.example.data.repositories.contract.DashboardRepository
import javax.inject.Inject

class DashboardDataUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun getDashboard(): NetworkResult<DashboardResponse> {
        return dashboardRepository.getDashboard()
    }

}