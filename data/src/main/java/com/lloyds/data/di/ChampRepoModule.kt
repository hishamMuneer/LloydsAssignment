package com.lloyds.data.di

import com.lloyds.data.repository.ChampionsRepoImpl
import com.lloyds.domain.repository.ChampionsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ChampRepoModule {

    @Binds
    abstract fun bindChampRepo(championsRepoImpl: ChampionsRepoImpl): ChampionsRepo
}
