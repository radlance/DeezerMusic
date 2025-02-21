package com.radlance.deezermusic.presentation.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.deezermusic.domain.player.PlayerRepository
import com.radlance.deezermusic.domain.track.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {
    private val _trackState = MutableStateFlow(TrackState())
    val trackState: StateFlow<TrackState> = _trackState.asStateFlow()

    val isFinished = playerRepository.getFinishState().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = false
    )

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

    fun stopTrack() {
        playerRepository.stop()
        _trackState.update { currentState ->
            currentState.copy(currentTrack = null, isPlaying = false)
        }
    }
}