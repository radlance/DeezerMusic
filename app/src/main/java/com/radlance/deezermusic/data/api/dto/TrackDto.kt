package com.radlance.deezermusic.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackDto(
    val id: Int,
    val readable: Boolean = false,
    val title: String,
    @SerialName("title_short") val titleShort: String,
    val link: String,
    val duration: Int,
    val rank: Int,
    @SerialName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerialName("explicit_content_type") val explicitContentType: Int,
    @SerialName("explicit_content_cover") val explicitContentCover: Int,
    val preview: String,
    @SerialName("md5_image") val md5Image: String,
    val position: Int,
    val artist: ArtistDto,
    val album: AlbumDto,
    val type: String
)
