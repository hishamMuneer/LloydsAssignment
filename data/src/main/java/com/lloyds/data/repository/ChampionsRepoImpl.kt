package com.lloyds.data.repository

import com.lloyds.data.repository.source.ChampDataSource
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.repository.ChampionsRepo
import com.lloyds.domain.shared.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChampionsRepoImpl @Inject constructor(private val champDataSource: ChampDataSource) :
    ChampionsRepo {
    override suspend fun getChampionMap(): Flow<Result<ChampionMap>> =
        champDataSource.getChampionMap()


    override suspend fun getChampion(champName: String): Flow<Result<Champion>> =
        champDataSource.getChampion(champName)
}