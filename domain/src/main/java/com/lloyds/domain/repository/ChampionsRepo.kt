package com.lloyds.domain.repository

import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import kotlinx.coroutines.flow.Flow

interface ChampionsRepo {

    suspend fun getChampionMap() : Flow<ChampionMap>
    suspend fun getChampion(champName: String) : Flow<Champion>

}