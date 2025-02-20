package com.radlance.deezermusic.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDto(
    val id: Long = 0,
    val name: String = "",
    val link: String = "",
    val picture: String = "",
    @SerialName("picture_small") val pictureSmall: String = "",
    @SerialName("picture_medium") val pictureMedium: String = "",
    @SerialName("picture_big") val pictureBig: String = "",
    @SerialName("picture_xl") val pictureXl: String = "",
    val radio: Boolean = false,
    val tracklist: String = "",
    val type: String = ""
)
