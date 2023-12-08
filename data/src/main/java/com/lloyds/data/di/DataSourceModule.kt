package com.lloyds.data.di

import com.lloyds.data.repository.source.ChampionDetailsDataSource
import com.lloyds.data.repository.source.ChampionDetailsDataSourceImpl
import com.lloyds.data.repository.source.ChampionMapDataSource
import com.lloyds.data.repository.source.ChampionMapDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindChampionMapDataSource(champDataSourceImpl: ChampionMapDataSourceImpl):
            ChampionMapDataSource

    @Binds
    abstract fun bindChampionDetailsDataSource(champDataSourceImpl: ChampionDetailsDataSourceImpl):
            ChampionDetailsDataSource
}