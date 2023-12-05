package com.lloyds.data.di

import com.lloyds.data.repository.source.ChampDataSource
import com.lloyds.data.repository.source.ChampDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindChampDataSource(champDataSourceImpl: ChampDataSourceImpl): ChampDataSource
}