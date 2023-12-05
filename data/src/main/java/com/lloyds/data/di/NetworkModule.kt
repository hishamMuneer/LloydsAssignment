package com.lloyds.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lloyds.data.BuildConfig
import com.lloyds.data.api.ChampionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.serialization.json.Json
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideChampService(retrofit: Retrofit): ChampionService {
        return retrofit.create(ChampionService::class.java)
    }

}