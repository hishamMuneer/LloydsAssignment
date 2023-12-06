package com.lloyds.data.repository.source

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.shared.Result
import kotlinx.coroutines.flow.Flow

interface ChampDataSource {
    suspend fun getChampionMap(): Flow<Result<ChampionMap>>

    suspend fun getChampion(champion: String): Flow<Result<Champion>>
}