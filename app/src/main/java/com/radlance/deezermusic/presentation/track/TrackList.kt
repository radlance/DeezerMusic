package com.radlance.deezermusic.presentation.track

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.deezermusic.domain.track.Album
import com.radlance.deezermusic.domain.track.Artist
import com.radlance.deezermusic.domain.track.Track
import com.radlance.deezermusic.presentation.ui.theme.DeezerMusicTheme

@Composable
fun TrackList(
    trackList: List<Track>,
    modifier: Modifier = Modifier,
    trackViewModel: TrackViewModel = hiltViewModel()
) {
    val trackState by trackViewModel.trackState.collectAsState()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(items = trackList, key = { track -> track.id }) { track ->
            TrackCard(
                track = track,
                isFocused = trackState.currentTrack == track,
                isPlaying = trackState.isPlaying,
                modifier = Modifier.clickable {
                    when {
                        trackState.currentTrack != track -> trackViewModel.playTrack(track)
                        trackState.isPlaying -> trackViewModel.pauseTrack()
                        else -> trackViewModel.resumeTrack()
                    }
                }
            )
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
            }
        )
    }
}