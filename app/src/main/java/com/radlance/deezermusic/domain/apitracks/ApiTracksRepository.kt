package com.radlance.deezermusic.domain.apitracks

interface ApiTracksRepository {
    suspend fun loadChart(): ApiTracksResult
    suspend fun loadTracksByQuery(query: String): ApiTracksResult
}