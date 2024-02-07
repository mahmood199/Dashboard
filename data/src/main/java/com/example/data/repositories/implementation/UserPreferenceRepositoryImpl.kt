package com.example.data.repositories.implementation

import com.example.data.local.PreferencesDataStore
import com.example.data.model.local.UserPreferences
import com.example.data.repositories.contract.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferenceRepositoryImpl @Inject constructor(
    private val dataStore: PreferencesDataStore,
) : UserPreferenceRepository {

    override fun getUserPreferences(): Flow<UserPreferences> {
        return dataStore.userPreferencesFlow
    }

    override suspend fun setAuthToken() {
        dataStore.setBearerToken()
    }

}