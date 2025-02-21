package com.radlance.deezermusic.presentation.track

import com.radlance.deezermusic.domain.track.Track

data class TrackState(
    val currentTrack: Track? = null,
    val isPlaying: Boolean = false
)
