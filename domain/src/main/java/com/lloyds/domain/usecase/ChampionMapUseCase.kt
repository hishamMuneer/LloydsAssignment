package com.lloyds.domain.usecase

import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.repository.ChampionsRepo
import com.lloyds.domain.shared.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ChampionMapUseCase @Inject constructor(private val championsRepo: ChampionsRepo) {
    suspend operator fun invoke(): Flow<Result<ChampionMap>> = championsRepo.getChampionMap()
}