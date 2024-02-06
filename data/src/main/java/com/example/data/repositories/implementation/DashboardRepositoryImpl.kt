package com.example.data.repositories.implementation

import com.example.core_network.NetworkResult
import com.example.data.model.response.DashboardResponse
import com.example.data.remote.DashboardRemoteDataSource
import com.example.data.repositories.contract.DashboardRepository
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val dataSource: DashboardRemoteDataSource,
) : DashboardRepository {

    override suspend fun getDashboard(): NetworkResult<DashboardResponse> {
        return dataSource.getDashboardData()
    }

}