package com.radlance.deezermusic.presentation.track

import com.radlance.deezermusic.domain.player.PlayerRepository
import com.radlance.deezermusic.domain.track.Track

interface PlaybackState {
    fun play(track: Track, playerRepository: PlayerRepository): PlaybackState

    object Stopped : PlaybackState {
        override fun play(track: Track, playerRepository: PlayerRepository): PlaybackState {
            playerRepository.prepare(track.preview)
            playerRepository.play(track.preview)
            return Playing(track)
        }
    }

    data class Playing(val track: Track) : PlaybackState {
        override fun play(track: Track, playerRepository: PlayerRepository): PlaybackState {
            return if (this.track == track) {
                playerRepository.pause()
                Paused(track)
            } else {
                val preview = track.preview
                playerRepository.prepare(preview)
                playerRepository.play(preview)
                Playing(track)
            }
        }
    }

    data class Paused(val track: Track) : PlaybackState {
        override fun play(track: Track, playerRepository: PlayerRepository): PlaybackState {
            val preview = track.preview
            return if (this.track == track) {
                playerRepository.play(preview)
                Playing(track)
            } else {
                playerRepository.prepare(preview)
                playerRepository.play(preview)
                Playing(track)
            }
        }
    }
}

