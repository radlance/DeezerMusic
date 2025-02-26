package com.radlance.deezermusic.app.navigation

import android.content.ComponentName
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.session.SessionToken
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.deezermusic.R
import com.radlance.deezermusic.presentation.apitracks.ApiTracksScreen
import com.radlance.deezermusic.presentation.track.PlaybackService
import com.radlance.deezermusic.presentation.track.TrackViewModel
import com.radlance.deezermusic.presentation.track.rememberMediaController

@Composable
fun NavGraph(
    bottomNavigationState: BottomNavigationState,
    modifier: Modifier = Modifier,
    trackViewModel: TrackViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val loadTrackDetailsUiState by trackViewModel.loadTrackDetailsUiState.collectAsState()
    val trackState by trackViewModel.trackState.collectAsState()
    val sessionToken = SessionToken(context, ComponentName(context, PlaybackService::class.java))
    val mediaController = rememberMediaController(context, sessionToken)

    Column(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController = bottomNavigationState.navHostController,
            startDestination = ApiTracks,
            enterTransition = {
                fadeIn(animationSpec = tween(durationMillis = 300))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 300))
            },
            modifier = Modifier.weight(1f)
        ) {
            composable<ApiTracks> {
                ApiTracksScreen(mediaController = mediaController, trackViewModel = trackViewModel)
            }

            composable<DownloadedTracks> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.downloaded))
                }
            }
        }

        loadTrackDetailsUiState.Show(
            isPlayed = trackState.isPlaying,
            onPlayClick = {
                if (trackState.isPlaying) {
                    mediaController?.pause()
                } else {
                    mediaController?.play()
                }
            },
            onPreviousClick = { mediaController?.seekToPrevious() },
            onNextClick = { mediaController?.seekToNext() }
        )
    }
}