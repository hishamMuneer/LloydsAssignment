package com.lloyds.data.repository.source

import com.lloyds.data.api.ChampionService
import com.lloyds.data.mapper.ApiModelMapper
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChampDataSourceImpl @Inject constructor(
    private val service: ChampionService,
    private val apiMapper: ApiModelMapper
) : ChampDataSource {
    override suspend fun getChampionMap(): Flow<ChampionMap> {
        return flow {
            val championList = service.getChampionList()
            emit(apiMapper.mapApiChampionMapToChampionMap(championList))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getChampion(champion: String): Flow<Champion> {
        return flow {
            val champ = service.getChampion(champion)
            emit(apiMapper.mapApiChampionToChampion(champ))
        }.flowOn(Dispatchers.IO)
    }

}