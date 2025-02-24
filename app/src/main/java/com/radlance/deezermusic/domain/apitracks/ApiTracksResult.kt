package com.radlance.deezermusic.domain.apitracks

import com.radlance.deezermusic.domain.track.Track

interface ApiTracksResult {
    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapSuccess(tracks: List<Track>): T
        fun mapError(message: String): T
    }

    data class Success(private val tracks: List<Track>) : ApiTracksResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess(tracks)
        }

    }

    data class Error(private val message: String) : ApiTracksResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(message)
        }
    }
}