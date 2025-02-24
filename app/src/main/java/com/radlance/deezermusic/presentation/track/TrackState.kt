package com.radlance.deezermusic.presentation.track

import androidx.media3.common.MediaItem

data class TrackState(
    val currentMediaItem: MediaItem? = null,
    val isPlaying: Boolean = false
)
