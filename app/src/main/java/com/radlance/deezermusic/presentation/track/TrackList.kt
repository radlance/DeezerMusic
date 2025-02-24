package com.radlance.deezermusic.presentation.track

import android.content.ComponentName
import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.SessionToken
import com.radlance.deezermusic.R
import com.radlance.deezermusic.domain.track.Album
import com.radlance.deezermusic.domain.track.Artist
import com.radlance.deezermusic.domain.track.Track
import com.radlance.deezermusic.presentation.ui.theme.DeezerMusicTheme

@Composable
fun TrackList(
    trackList: List<Track>,
    label: String,
    modifier: Modifier = Modifier,
    trackViewModel: TrackViewModel = viewModel()
) {
    val trackState by trackViewModel.trackState.collectAsState()

    val context = LocalContext.current
    val sessionToken = SessionToken(context, ComponentName(context, PlaybackService::class.java))
    val mediaController = rememberMediaController(context, sessionToken)

    val mediaItems = trackList.map { track ->
        MediaItem.Builder()
            .setMediaId(track.id.toString())
            .setUri(Uri.parse(track.preview))
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setArtist(track.artist.name)
                    .setTitle(track.title)
                    .setArtworkUri(Uri.parse(track.album.coverXl))
                    .build()
            )
            .build()
    }


    DisposableEffect(mediaController) {
        val listener = object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                trackViewModel.setMediaItem(mediaItem)
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                trackViewModel.changePlayingState(isPlaying)
            }
        }

        mediaController?.addListener(listener)

        onDispose {
            mediaController?.removeListener(listener)
        }
    }

    val scrollState = rememberLazyListState()
    val firstVisibleItemIndex by remember {
        derivedStateOf { scrollState.firstVisibleItemIndex }
    }

    val firstVisibleItemScrollOffset by remember {
        derivedStateOf { scrollState.firstVisibleItemScrollOffset }
    }

    val textSize by animateFloatAsState(
        targetValue = if (firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset == 0) {
            24f
        } else {
            16f
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (trackList.isNotEmpty()) {
            Text(
                text = label,
                fontSize = textSize.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            LazyColumn(
                state = scrollState,
                modifier = modifier
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                0f to Color.Transparent,
                                0.01f to Color.Red,
                                0.99f to Color.Red,
                                1f to Color.Transparent
                            ), blendMode = BlendMode.DstIn
                        )
                    },
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(items = trackList, key = { track -> track.id }) { track ->
                    with(trackState) {
                        TrackCard(
                            track = track,
                            isFocused = currentMediaItem?.mediaId == track.id.toString(),
                            isPlaying = isPlaying,
                            modifier = Modifier.clickable {
                                mediaController?.let { controller ->
                                    if (currentMediaItem?.mediaId == track.id.toString()) {
                                        if (isPlaying) {
                                            controller.pause()
                                        } else {
                                            controller.play()
                                        }
                                    } else {
                                        mediaController.setMediaItems(mediaItems)
                                        controller.seekTo(
                                            mediaItems.indexOf(mediaItems.first { it.mediaId == track.id.toString() }),
                                            0
                                        )
                                        controller.play()
                                    }
                                }
                            }
                        )

                    }
                }
            }
        } else {
            Text(text = stringResource(R.string.nothing_found))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrackListPreview() {
    DeezerMusicTheme {
        TrackList(
            trackList = List(20) {
                Track(
                    id = (77619608 + it).toLong(),
                    readable = true,
                    title = "À la Croix",
                    titleShort = "À la Croix",
                    titleVersion = "",
                    link = "https://www.deezer.com/track/77619608",
                    duration = 493,
                    rank = 317453,
                    explicitLyrics = false,
                    explicitContentType = 0,
                    explicitContentCover = 2,
                    preview = "https://cdn-preview-6.dzcdn.net/stream/c-6da675987773e53be6706065cc4b3fb1-1.mp3",
                    md5Image = "836b683f4ae9116f0408d087e86566d8",
                    position = 0,
                    explicitContentLyrics = 0,
                    artist = Artist(
                        id = 88666,
                        name = "Stéphane Quéry",
                        link = "https://www.deezer.com/artist/88666",
                        picture = "https://api.deezer.com/artist/88666/image",
                        pictureSmall = "https://cdn-images.dzcdn.net/images/artist/836b683f4ae9116f0408d087e86566d8/56x56-000000-80-0-0.jpg",
                        pictureMedium = "https://cdn-images.dzcdn.net/images/artist/836b683f4ae9116f0408d087e86566d8/250x250-000000-80-0-0.jpg",
                        pictureBig = "https://cdn-images.dzcdn.net/images/artist/836b683f4ae9116f0408d087e86566d8/500x500-000000-80-0-0.jpg",
                        pictureXl = "https://cdn-images.dzcdn.net/images/artist/836b683f4ae9116f0408d087e86566d8/1000x1000-000000-80-0-0.jpg",
                        radio = false,
                        tracklist = "https://api.deezer.com/artist/88666/top?limit=50",
                        type = "artist"
                    ),
                    album = Album(
                        id = 7714218,
                        title = "Écrivons L\"Histoire",
                        cover = "https://api.deezer.com/album/7714218/image",
                        coverSmall = "https://cdn-images.dzcdn.net/images/cover/836b683f4ae9116f0408d087e86566d8/56x56-000000-80-0-0.jpg",
                        coverMedium = "https://cdn-images.dzcdn.net/images/cover/836b683f4ae9116f0408d087e86566d8/250x250-000000-80-0-0.jpg",
                        coverBig = "https://cdn-images.dzcdn.net/images/cover/836b683f4ae9116f0408d087e86566d8/500x500-000000-80-0-0.jpg",
                        coverXl = "https://cdn-images.dzcdn.net/images/cover/836b683f4ae9116f0408d087e86566d8/1000x1000-000000-80-0-0.jpg",
                        md5Image = "836b683f4ae9116f0408d087e86566d8",
                        tracklist = "https://api.deezer.com/album/7714218/tracks",
                        type = "album"
                    ),
                    type = "track"
                )
            },
            label = stringResource(R.string.charts)
        )
    }
}