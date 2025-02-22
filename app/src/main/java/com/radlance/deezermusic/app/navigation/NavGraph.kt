package com.radlance.deezermusic.app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.deezermusic.R
import com.radlance.deezermusic.presentation.apitracks.ApiTracksScreen

@Composable
fun NavGraph(
    bottomNavigationState: BottomNavigationState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = bottomNavigationState.navHostController,
        startDestination = ApiTracks,
        enterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 300))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 300))
        },
        modifier = modifier
    ) {
        composable<ApiTracks> {
            ApiTracksScreen()
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
}