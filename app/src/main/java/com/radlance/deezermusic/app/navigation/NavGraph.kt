package com.radlance.deezermusic.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    bottomNavigationState: BottomNavigationState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = bottomNavigationState.navHostController,
        startDestination = ApiTracks,
        modifier = modifier
    ) {
        composable<ApiTracks> {

        }

        composable<DownloadedTracks> {

        }
    }
}