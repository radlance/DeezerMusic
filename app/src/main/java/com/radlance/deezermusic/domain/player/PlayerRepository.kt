package com.radlance.deezermusic.domain.player

import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun play(url: String)
    fun resume()
    fun pause()
    fun stop()
    fun release()
    fun getFinishState(): Flow<Boolean>
}