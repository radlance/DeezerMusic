package com.radlance.deezermusic.presentation.apitracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.deezermusic.domain.apitracks.ApiTracksRepository
import com.radlance.deezermusic.domain.apitracks.ApiTracksResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiTracksViewModel @Inject constructor(
    private val apiTracksRepository: ApiTracksRepository,
    private val mapper: ApiTracksResult.Mapper<ApiTracksResultUiState>,
) : ViewModel() {
    private val _loadChartResultUiState =
        MutableStateFlow<ApiTracksResultUiState>(ApiTracksResultUiState.Initial)
    val loadChartResultUiState: StateFlow<ApiTracksResultUiState> =
        _loadChartResultUiState.onStart {
            loadChart()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ApiTracksResultUiState.Initial
        )

    fun loadChart() {
        viewModelScope.launch {
            _loadChartResultUiState.value = ApiTracksResultUiState.Loading
            val result = apiTracksRepository.loadChart()
            _loadChartResultUiState.value = result.map(mapper)
        }
    }
}