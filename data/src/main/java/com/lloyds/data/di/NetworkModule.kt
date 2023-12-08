package com.lloyds.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lloyds.data.BuildConfig
import com.lloyds.data.api.ChampionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true // Skip unknown JSON properties without raising SerializationException.
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideChampService(retrofit: Retrofit): ChampionService {
        return retrofit.create(ChampionService::class.java)
    }
}