package com.radlance.deezermusic.domain.track

data class Album(
    val id: Int,
    val title: String,
    val cover: String,
    val coverSmall: String,
    val coverMedium: String,
    val coverBig: String,
    val coverXl: String,
    val md5Image: String,
    val tracklist: String,
    val type: String
)
