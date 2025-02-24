package com.radlance.deezermusic.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchResultDto(
    @SerialName("tracks") val content: FetchContentDto
)
