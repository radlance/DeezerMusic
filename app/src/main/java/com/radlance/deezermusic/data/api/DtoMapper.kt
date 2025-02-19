package com.radlance.deezermusic.data.api

import com.radlance.deezermusic.data.api.dto.AlbumDto
import com.radlance.deezermusic.data.api.dto.ArtistDto
import com.radlance.deezermusic.data.api.dto.TrackDto
import com.radlance.deezermusic.domain.track.Album
import com.radlance.deezermusic.domain.track.Artist
import com.radlance.deezermusic.domain.track.Track

abstract class DtoMapper {
    protected fun TrackDto.toTrack(): Track {
        return Track(
            id = id,
            title = title,
            titleShort = titleShort,
            link = link,
            duration = duration,
            rank = rank,
            explicitLyrics = explicitLyrics,
            explicitContentType = explicitContentType,
            explicitContentCover = explicitContentCover,
            preview = preview,
            md5Image = md5Image,
            position = position,
            artist = artist.toArtist(),
            album = album.toAlbum(),
            type = type
        )
    }

    protected fun ArtistDto.toArtist(): Artist {
        return Artist(
            id = id,
            name = name,
            link = link,
            picture = picture,
            pictureSmall = pictureSmall,
            pictureMedium = pictureMedium,
            pictureBig = pictureBig,
            pictureXl = pictureXl,
            radio = radio,
            tracklist = tracklist,
            type = type
        )
    }

    protected fun AlbumDto.toAlbum(): Album {
        return Album(
            id = id,
            title = title,
            cover = cover,
            coverSmall = coverSmall,
            coverMedium = coverMedium,
            coverBig = coverBig,
            coverXl = coverXl,
            md5Image = md5Image,
            tracklist = tracklist,
            type = type
        )
    }
}