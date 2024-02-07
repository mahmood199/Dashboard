package com.example.composedweather.ui.feature.home_container

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedweather.ui.feature.dashboard.DashboardViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class HomeContainerViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(HomeContainerViewState.default())
    val state = _state.asStateFlow()

    private val stateLock = Mutex()

    fun updateSelectedScreen(it: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateState {
                copy(currentPage = it)
            }
        }
    }

    private suspend fun updateState(updater: HomeContainerViewState.() -> HomeContainerViewState) {
        stateLock.withLock {
            _state.value = _state.value.updater()
        }
    }


}