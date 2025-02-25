package com.radlance.deezermusic.app.di

import com.radlance.deezermusic.data.api.ApiTracksRepositoryImpl
import com.radlance.deezermusic.domain.apitracks.ApiTracksRepository
import com.radlance.deezermusic.domain.apitracks.ApiTracksResult
import com.radlance.deezermusic.presentation.apitracks.ApiTracksResultMapper
import com.radlance.deezermusic.presentation.apitracks.ApiTracksResultUiState
import com.radlance.deezermusic.presentation.track.TrackMapper
import com.radlance.deezermusic.presentation.track.TrackUiState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TracksModule {
    @Binds
    fun provideApiTracksRepository(apiTracksRepositoryImpl: ApiTracksRepositoryImpl): ApiTracksRepository

    @Binds
    fun provideApiTracksResultMapper(apiTracksResultMapper: ApiTracksResultMapper): ApiTracksResult.Mapper<ApiTracksResultUiState>

    @Binds
    fun provideTrackResultMapper(apiTracksResultMapper: TrackMapper): ApiTracksResult.Mapper<TrackUiState>
}