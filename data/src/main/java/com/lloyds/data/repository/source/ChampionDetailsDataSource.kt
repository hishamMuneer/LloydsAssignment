package com.lloyds.data.repository.source

import com.lloyds.domain.model.Champion
import com.lloyds.domain.shared.Result
import kotlinx.coroutines.flow.Flow

interface ChampionDetailsDataSource {
    suspend fun getChampion(id: String): Flow<Result<Champion>>
}