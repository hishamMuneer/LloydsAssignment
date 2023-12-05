package com.lloyds.data.repository

import com.lloyds.data.repository.source.ChampDataSource
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.repository.ChampionsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChampionsRepoImpl @Inject constructor(private val champDataSource: ChampDataSource) :
    ChampionsRepo {
    override suspend fun getChampionMap(): Flow<ChampionMap> =
        champDataSource.getChampionMap()


    override suspend fun getChampion(champName: String): Flow<Champion> =
        champDataSource.getChampion(champName)
}