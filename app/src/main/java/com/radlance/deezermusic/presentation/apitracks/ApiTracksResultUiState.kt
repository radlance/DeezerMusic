package com.radlance.deezermusic.presentation.apitracks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.media3.session.MediaController
import com.radlance.deezermusic.R
import com.radlance.deezermusic.domain.track.Track
import com.radlance.deezermusic.presentation.track.TrackList
import com.radlance.deezermusic.presentation.track.TrackViewModel

interface ApiTracksResultUiState {
    @Composable
    fun Show(
        label: String,
        onRetryClick: () -> Unit,
        mediaController: MediaController?,
        trackViewModel: TrackViewModel
    )

    data class Success(private val tracks: List<Track>) : ApiTracksResultUiState {
        @Composable
        override fun Show(
            label: String,
            onRetryClick: () -> Unit,
            mediaController: MediaController?,
            trackViewModel: TrackViewModel
        ) {
            TrackList(
                trackList = tracks,
                label = label,
                mediaController = mediaController,
                trackViewModel = trackViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    data class Error(private val message: String) : ApiTracksResultUiState {
        @Composable
        override fun Show(
            label: String,
            onRetryClick: () -> Unit,
            mediaController: MediaController?,
            trackViewModel: TrackViewModel
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = message, textAlign = TextAlign.Center)
                Spacer(Modifier.height(6.dp))
                Button(onRetryClick) {
                    Text(text = stringResource(R.string.try_again))
                }
            }
        }
    }

    object Loading : ApiTracksResultUiState {
        @Composable
        override fun Show(
            label: String,
            onRetryClick: () -> Unit,
            mediaController: MediaController?,
            trackViewModel: TrackViewModel
        ) {
            CircularProgressIndicator()
        }
    }

    object Initial : ApiTracksResultUiState {
        @Composable
        override fun Show(
            label: String,
            onRetryClick: () -> Unit,
            mediaController: MediaController?,
            trackViewModel: TrackViewModel
        ) {
        }
    }
}