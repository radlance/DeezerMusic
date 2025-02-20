package com.radlance.deezermusic.presentation.track

import androidx.lifecycle.ViewModel
import com.radlance.deezermusic.domain.player.PlayerRepository
import com.radlance.deezermusic.domain.track.Track
import kotlinx.coroutines.flow.MutableStateFlow

abstract class TrackViewModel(
    private val playerRepository: PlayerRepository
) : ViewModel() {
    private val playbackState = MutableStateFlow<PlaybackState>(PlaybackState.Stopped)

    fun playTrack(track: Track) {
        playbackState.value = playbackState.value.play(track, playerRepository)
    }

    fun stop() = playerRepository.stop()
}