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
    private val mapper: ApiTracksResult.Mapper<ApiTracksResultUiState>
) : ViewModel() {
    private val _loadTracksResultUiState =
        MutableStateFlow<ApiTracksResultUiState>(ApiTracksResultUiState.Initial)
    val loadTracksResultUiState: StateFlow<ApiTracksResultUiState> =
        _loadTracksResultUiState.onStart {
            loadChart()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ApiTracksResultUiState.Initial
        )

    fun loadChart() {
        viewModelScope.launch {
            _loadTracksResultUiState.value = ApiTracksResultUiState.Loading
            val result = apiTracksRepository.loadChart()
            _loadTracksResultUiState.value = result.map(mapper)
        }
    }

    fun searchTracksByQuery(query: String) {
        viewModelScope.launch {
            _loadTracksResultUiState.value = ApiTracksResultUiState.Loading
            val result = apiTracksRepository.loadTracksByQuery(query)
            _loadTracksResultUiState.value = result.map(mapper)
        }
    }
}