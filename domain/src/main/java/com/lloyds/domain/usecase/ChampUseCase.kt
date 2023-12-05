package com.lloyds.domain.usecase

import com.lloyds.domain.repository.ChampionsRepo
import javax.inject.Inject

class ChampionUseCase @Inject constructor(private val movieRepo: ChampionsRepo) {
    suspend operator fun invoke() = movieRepo.getChampionMap()
    suspend operator fun invoke(champName: String) = movieRepo.getChampion(champName)
}