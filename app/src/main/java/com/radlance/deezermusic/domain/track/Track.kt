package com.radlance.deezermusic.domain.track

data class Track(
    val id: Long,
    val readable: Boolean,
    val title: String,
    val titleShort: String,
    val titleVersion: String,
    val link: String,
    val duration: Int,
    val rank: Int,
    val explicitLyrics: Boolean,
    val explicitContentLyrics: Int,
    val explicitContentType: Int,
    val explicitContentCover: Int,
    val preview: String,
    val md5Image: String,
    val position: Int,
    val artist: Artist,
    val album: Album,
    val type: String
)
