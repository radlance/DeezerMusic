package com.radlance.deezermusic.presentation.track

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.radlance.deezermusic.R
import com.radlance.deezermusic.domain.track.Album
import com.radlance.deezermusic.domain.track.Artist
import com.radlance.deezermusic.domain.track.Track
import com.radlance.deezermusic.presentation.ui.theme.DeezerMusicTheme

@Composable
fun TrackCard(
    track: Track,
    isFocused: Boolean,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(52.dp), contentAlignment = Alignment.Center) {
            SubcomposeAsyncImage(
                model = track.album.cover,
                error = {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Black.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MusicNote,
                            contentDescription = stringResource(R.string.play_pause_track),
                            tint = Color.White
                        )
                    }
                },
                contentDescription = stringResource(R.string.track_cover),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(6.dp))
            )

            androidx.compose.animation.AnimatedVisibility(
                visible = isFocused,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                if (isFocused) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Black.copy(alpha = 0.3f))
                    ) {
                        val icon = if (isPlaying) {
                            Icons.Default.Pause
                        } else {
                            Icons.Default.PlayArrow
                        }

                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(R.string.play_pause_track),
                            tint = Color.White
                        )
                    }
                }
            }

        }

        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = track.title,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(text = track.artist.name, fontSize = 12.sp, modifier = Modifier.alpha(0.5f))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrackCardPreview() {
    DeezerMusicTheme {
        TrackCard(
            track = Track(
                id = 77619608,
                readable = true,
                title = "À la Croix",
                titleShort = "À la Croix",
                titleVersion = "",
                link = "https://www.deezer.com/track/77619608",
                duration = 493,
                rank = 317453,
                explicitLyrics = false,
                explicitContentLyrics = 0,
                explicitContentType = 0,
                explicitContentCover = 2,
                preview = "https://cdn-preview-6.dzcdn.net/stream/c-6da675987773e53be6706065cc4b3fb1-1.mp3",
                md5Image = "836b683f4ae9116f0408d087e86566d8",
                position = 0,
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
            ),
            isFocused = false,
            isPlaying = true
        )
    }
}