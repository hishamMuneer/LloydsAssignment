package com.lloyds.data.repository

import com.lloyds.data.repository.source.ChampionDetailsDataSource
import com.lloyds.data.repository.source.ChampionMapDataSource
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.repository.ChampionsRepo
import com.lloyds.domain.shared.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ChampionsRepoImpl @Inject constructor(
    private val championMapDataSource: ChampionMapDataSource,
    private val championDetailsDataSource: ChampionDetailsDataSource
) : ChampionsRepo {
    override suspend fun getChampionMap(): Flow<Result<ChampionMap>> =
        championMapDataSource.getChampionMap()


    override suspend fun getChampion(id: String): Flow<Result<Champion>> =
        championDetailsDataSource.getChampion(id)
}