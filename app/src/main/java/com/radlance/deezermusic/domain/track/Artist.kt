package com.radlance.deezermusic.domain.track

data class Artist(
    val id: Int,
    val name: String,
    val link: String,
    val picture: String,
    val pictureSmall: String,
    val pictureMedium: String,
    val pictureBig: String,
    val pictureXl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)
