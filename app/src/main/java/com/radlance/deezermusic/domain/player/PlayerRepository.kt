package com.radlance.deezermusic.domain.player

interface PlayerRepository {
    fun play(url: String)
    fun pause()
    fun stop()
    fun release()
}