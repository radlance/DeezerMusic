package com.radlance.deezermusic.presentation.track

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.radlance.deezermusic.domain.track.Track

interface TrackUiState {
    @Composable
    fun Show(
        isPlayed: Boolean,
        onPlayClick: () -> Unit,
        onPreviousClick: () -> Unit,
        onNextClick: () -> Unit
    )

    data class Success(private val foundedTrack: Track) : TrackUiState {
        @Composable
        override fun Show(
            isPlayed: Boolean,
            onPlayClick: () -> Unit,
            onPreviousClick: () -> Unit,
            onNextClick: () -> Unit
        ) {
            Box(modifier = Modifier.height(76.dp), contentAlignment = Alignment.CenterStart) {
                TrackCard(
                    track = foundedTrack,
                    isFocused = true,
                    isPlaying = isPlayed,
                    onPlayClick = onPlayClick,
                    isMiniPlayer = true,
                    onPreviousClick = onPreviousClick,
                    onNextClick = onNextClick,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    }

    data class Error(private val message: String) : TrackUiState {
        @Composable
        override fun Show(
            isPlayed: Boolean,
            onPlayClick: () -> Unit,
            onPreviousClick: () -> Unit,
            onNextClick: () -> Unit
        ) {
        }
    }

    object Initial : TrackUiState {
        @Composable
        override fun Show(
            isPlayed: Boolean,
            onPlayClick: () -> Unit,
            onPreviousClick: () -> Unit,
            onNextClick: () -> Unit
        ) {
        }
    }
}