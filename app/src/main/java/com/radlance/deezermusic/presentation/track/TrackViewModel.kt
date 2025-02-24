package com.radlance.deezermusic.presentation.track

import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TrackViewModel : ViewModel() {
    private val _trackState = MutableStateFlow(TrackState())
    val trackState: StateFlow<TrackState>
        get() = _trackState.asStateFlow()

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
}