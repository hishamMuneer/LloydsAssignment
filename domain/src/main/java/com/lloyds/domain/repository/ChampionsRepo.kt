package com.lloyds.domain.repository

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.shared.Result
import kotlinx.coroutines.flow.Flow

interface ChampionsRepo {

    suspend fun getChampionMap(): Flow<Result<ChampionMap>>
    suspend fun getChampion(champName: String): Flow<Result<Champion>>

}