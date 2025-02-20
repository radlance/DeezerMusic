package com.radlance.deezermusic.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackDto(
    val id: Long = 0,
    val readable: Boolean = false,
    val title: String = "",
    @SerialName("title_short") val titleShort: String = "",
    @SerialName("title_version") val titleVersion: String = "",
    val link: String = "",
    val duration: Int = 0,
    val rank: Int = 0,
    @SerialName("explicit_lyrics") val explicitLyrics: Boolean = false,
    @SerialName("explicit_content_lyrics") val explicitContentLyrics: Int = 0,
    @SerialName("explicit_content_type") val explicitContentType: Int = 0,
    @SerialName("explicit_content_cover") val explicitContentCover: Int = 0,
    val preview: String = "",
    @SerialName("md5_image") val md5Image: String = "",
    val position: Int = 0,
    val artist: ArtistDto,
    val album: AlbumDto,
    val type: String
)
