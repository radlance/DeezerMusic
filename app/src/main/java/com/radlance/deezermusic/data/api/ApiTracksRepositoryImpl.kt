package com.radlance.deezermusic.data.api

import com.radlance.deezermusic.R
import com.radlance.deezermusic.common.ResourceProvider
import com.radlance.deezermusic.domain.apitracks.ApiTracksRepository
import com.radlance.deezermusic.domain.apitracks.ApiTracksResult
import java.net.UnknownHostException
import javax.inject.Inject

class ApiTracksRepositoryImpl @Inject constructor(
    private val service: DeezerService,
    private val resourceProvider: ResourceProvider
) : ApiTracksRepository, DtoMapper() {
    override suspend fun loadChart(): ApiTracksResult {
        return try {
            val chart = service.loadChart()
            ApiTracksResult.Success(chart.content.tracks.map { trackDto -> trackDto.toTrack() })
        } catch (e: UnknownHostException) {
            ApiTracksResult.Error(resourceProvider.getString(R.string.no_connection))
        } catch (e: Exception) {
            ApiTracksResult.Error(e.message ?: resourceProvider.getString(R.string.unknown_error))
        }
    }

    override suspend fun loadTracksByQuery(query: String): ApiTracksResult {
        return try {
            val foundedTracks = service.loadTracksByQuery(query)
            ApiTracksResult.Success(foundedTracks.tracks.map { trackDto -> trackDto.toTrack() })
        } catch (e: UnknownHostException) {
            ApiTracksResult.Error(resourceProvider.getString(R.string.no_connection))
        } catch (e: Exception) {
            ApiTracksResult.Error(e.message ?: resourceProvider.getString(R.string.unknown_error))
        }
    }

    override suspend fun loadTrackDetailsById(trackId: String): ApiTracksResult {
        return try {
            val foundedTrack = service.loadTrackDetailsById(trackId)
            ApiTracksResult.Success(listOf(foundedTrack.toTrack()))
        } catch (e: UnknownHostException) {
            ApiTracksResult.Error(resourceProvider.getString(R.string.no_connection))
        } catch (e: Exception) {
            ApiTracksResult.Error(e.message ?: resourceProvider.getString(R.string.unknown_error))
        }
    }
}