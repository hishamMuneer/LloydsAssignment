package com.lloyds.data.repository.source

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import kotlinx.coroutines.flow.Flow

interface ChampDataSource {
    suspend fun getChampionMap(): Flow<ChampionMap>

    suspend fun getChampion(champion: String): Flow<Champion>
}