package com.radlance.deezermusic.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDto(
    val id: Long = 0,
    val title: String = "",
    val cover: String = "",
    @SerialName("cover_small") val coverSmall: String = "",
    @SerialName("cover_medium") val coverMedium: String = "",
    @SerialName("cover_big") val coverBig: String = "",
    @SerialName("cover_xl") val coverXl: String = "",
    @SerialName("md5_image") val md5Image: String = "",
    val tracklist: String = "",
    val type: String = ""
)
