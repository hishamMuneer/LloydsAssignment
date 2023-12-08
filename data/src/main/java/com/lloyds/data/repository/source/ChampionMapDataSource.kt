package com.lloyds.data.repository.source

import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.shared.Result
import kotlinx.coroutines.flow.Flow

interface ChampionMapDataSource {
    suspend fun getChampionMap(): Flow<Result<ChampionMap>>
}