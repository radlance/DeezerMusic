package com.radlance.deezermusic.presentation.apitracks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ApiTracksScreen(
    modifier: Modifier = Modifier,
    viewModel: ApiTracksViewModel = hiltViewModel()
) {
    val loadChartResultUiState by viewModel.loadChartResultUiState.collectAsState()
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        loadChartResultUiState.Show(
            onRetryClick = viewModel::loadChart,
            onTrackClick = { track -> viewModel.playTrack(track) }
        )
    }
}