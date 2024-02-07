package com.example.data.remote

import com.example.core_network.NetworkResult
import com.example.core_network.ResponseProcessor
import com.example.data.model.response.DashboardResponse
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import java.io.IOException
import javax.inject.Inject

class DashboardRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient,
    private val responseProcessor: ResponseProcessor,
    private val gson: Gson
) {

    companion object {
        const val URL = "https://api.inopenapp.com/api/v1/dashboardNew"
        const val AUTHORIZATION_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
    }

    suspend fun getDashboardData(authToken: String): NetworkResult<DashboardResponse> {
        return try {
            val response = httpClient.get(URL) {
                headers.append(HttpHeaders.Authorization, authToken)
            }
            val result = responseProcessor.getResultFromResponse<DashboardResponse>(gson, response)
            result
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) {
                NetworkResult.Exception(Throwable("Please your internet connection"))
            } else {
                NetworkResult.Exception(Throwable("Something went wrong"))
            }
        }
    }

}