package com.radlance.deezermusic.presentation.track

import com.radlance.deezermusic.domain.apitracks.ApiTracksResult
import com.radlance.deezermusic.domain.track.Track
import javax.inject.Inject

class TrackMapper @Inject constructor(): ApiTracksResult.Mapper<TrackUiState> {
    override fun mapSuccess(tracks: List<Track>): TrackUiState {
        return TrackUiState.Success(tracks.first())
    }

    override fun mapError(message: String): TrackUiState {
        return TrackUiState.Error(message)
    }
}