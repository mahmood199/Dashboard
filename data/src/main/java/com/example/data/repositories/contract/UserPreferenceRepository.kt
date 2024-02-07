package com.example.data.repositories.contract

import com.example.data.model.local.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {

    fun getUserPreferences(): Flow<UserPreferences>

    suspend fun setAuthToken()
}