package com.radlance.deezermusic.app.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.radlance.deezermusic.common.BaseResourceProvider
import com.radlance.deezermusic.common.ResourceProvider
import com.radlance.deezermusic.data.api.DeezerService
import com.radlance.deezermusic.data.player.PlayerRepositoryImpl
import com.radlance.deezermusic.domain.player.PlayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideService(okHttpClient: OkHttpClient): DeezerService {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build().create<DeezerService>()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return BaseResourceProvider(context)
    }

    @Provides
    @Singleton
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    @Singleton
    fun providePlayerRepository(exoPlayer: ExoPlayer): PlayerRepository {
        return PlayerRepositoryImpl(exoPlayer)
    }
}