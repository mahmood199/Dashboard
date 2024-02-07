package com.example.data.repositories.di_module

import com.example.data.local.PreferencesDataStore
import com.example.data.remote.DashboardRemoteDataSource
import com.example.data.repositories.contract.DashboardRepository
import com.example.data.repositories.contract.UserPreferenceRepository
import com.example.data.repositories.implementation.DashboardRepositoryImpl
import com.example.data.repositories.implementation.UserPreferenceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(
        preferencesDataStore: PreferencesDataStore,
    ): UserPreferenceRepository {
        return UserPreferenceRepositoryImpl(
            dataStore = preferencesDataStore,
        )
    }

    @Provides
    @Singleton
    fun provideDashboardRepository(
        remoteDataSource: DashboardRemoteDataSource
    ): DashboardRepository {
        return DashboardRepositoryImpl(
            dataSource = remoteDataSource
        )
    }

}