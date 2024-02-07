package com.example.domain.user

import com.example.data.model.local.UserPreferences
import com.example.data.repositories.contract.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesUseCase @Inject constructor(
    private val repository: UserPreferenceRepository
) {

    fun getUserPreference(): Flow<UserPreferences> {
        return repository.getUserPreferences()
    }

}