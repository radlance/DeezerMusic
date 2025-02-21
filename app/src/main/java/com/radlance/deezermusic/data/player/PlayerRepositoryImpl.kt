package com.radlance.deezermusic.data.player

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.radlance.deezermusic.domain.player.PlayerRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val exoPlayer: ExoPlayer
) : PlayerRepository {

    override fun play(url: String) {
        exoPlayer.apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(url)))
            prepare()
            play()
        }
    }

    override fun getFinishState(): Flow<Boolean> {
        return callbackFlow {
            val listener = object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    trySend(state == Player.STATE_ENDED)
                }
            }
            exoPlayer.addListener(listener)
            awaitClose { exoPlayer.removeListener(listener) }
        }
    }

    override fun resume() = exoPlayer.play()
    override fun pause() = exoPlayer.pause()
    override fun stop() = exoPlayer.stop()
    override fun release() = exoPlayer.release()
}