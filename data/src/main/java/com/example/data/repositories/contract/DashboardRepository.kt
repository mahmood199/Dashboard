package com.example.data.repositories.contract

import com.example.core_network.NetworkResult
import com.example.data.model.response.DashboardResponse

interface DashboardRepository {

    suspend fun getDashboard(): NetworkResult<DashboardResponse>

}