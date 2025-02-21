package com.radlance.deezermusic.presentation.track

import androidx.lifecycle.ViewModel
import com.radlance.deezermusic.domain.player.PlayerRepository
import com.radlance.deezermusic.domain.track.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {
    private val _trackState = MutableStateFlow(TrackState())
    val trackState: StateFlow<TrackState>
        get() = _trackState.asStateFlow()

    fun playTrack(track: Track) {
        playerRepository.play(track.preview)
        _trackState.update { currentState ->
            currentState.copy(
                currentTrack = track,
                isPlaying = true
            )
        }
    }

    fun pauseTrack() {
        playerRepository.pause()
        _trackState.update { currentState -> currentState.copy(isPlaying = false) }
    }

    fun resumeTrack() {
        playerRepository.resume()
        _trackState.update { currentState -> currentState.copy(isPlaying = true) }
    }
}