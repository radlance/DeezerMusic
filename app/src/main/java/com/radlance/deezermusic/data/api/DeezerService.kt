package com.radlance.deezermusic.data.api

import com.radlance.deezermusic.data.api.dto.FetchContentDto
import com.radlance.deezermusic.data.api.dto.FetchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerService {
    @GET("chart")
    suspend fun loadChart(): FetchResultDto

    @GET("search")
    suspend fun loadTracksByQuery(@Query("q") query: String): FetchContentDto
}