package com.radlance.deezermusic.domain.player

interface PlayerRepository {
    fun prepare(url: String)
    fun play(url: String)
    fun pause()
    fun stop()
    fun release()
}