package com.radlance.deezermusic.presentation.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import com.radlance.deezermusic.domain.apitracks.ApiTracksRepository
import com.radlance.deezermusic.domain.apitracks.ApiTracksResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val apiTracksRepository: ApiTracksRepository,
    private val mapper: ApiTracksResult.Mapper<TrackUiState>
) : ViewModel() {
    private val _trackState = MutableStateFlow(TrackState())
    val trackState: StateFlow<TrackState>
        get() = _trackState.asStateFlow()

    private val _loadTrackDetailsUiState =
        MutableStateFlow<TrackUiState>(TrackUiState.Initial)
    val loadTrackDetailsUiState: StateFlow<TrackUiState>
        get() = _loadTrackDetailsUiState.asStateFlow()

    fun changePlayingState(isPlaying: Boolean) {
        _trackState.update { currentState ->
            currentState.copy(isPlaying = isPlaying)
        }
    }

    fun setMediaItem(mediaItem: MediaItem?) {
        _trackState.update { currentState ->
            currentState.copy(currentMediaItem = mediaItem)
        }
    }

    fun loadTrackDetailsById(trackId: String) {
        viewModelScope.launch {
            val result = apiTracksRepository.loadTrackDetailsById(trackId)
            _loadTrackDetailsUiState.value = result.map(mapper)
        }
    }
}