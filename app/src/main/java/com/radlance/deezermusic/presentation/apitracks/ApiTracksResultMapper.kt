package com.radlance.deezermusic.presentation.apitracks

import com.radlance.deezermusic.domain.apitracks.ApiTracksResult
import com.radlance.deezermusic.domain.track.Track
import javax.inject.Inject

class ApiTracksResultMapper @Inject constructor(): ApiTracksResult.Mapper<ApiTracksResultUiState> {
    override fun mapSuccess(tracks: List<Track>): ApiTracksResultUiState {
        return ApiTracksResultUiState.Success(tracks)
    }

    override fun mapError(message: String): ApiTracksResultUiState {
        return ApiTracksResultUiState.Error(message)
    }
}