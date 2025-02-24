package com.radlance.deezermusic.presentation.track

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors

@Composable
fun rememberMediaController(context: Context, sessionToken: SessionToken): MediaController? {
    var mediaController by remember { mutableStateOf<MediaController?>(null) }

    LaunchedEffect(sessionToken) {
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture.addListener(
            {
                mediaController = controllerFuture.get()
            },
            MoreExecutors.directExecutor()
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaController?.release()
        }
    }

    return mediaController
}