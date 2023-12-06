package com.lloyds.domain.usecase

import com.lloyds.domain.repository.ChampionsRepo
import javax.inject.Inject

class ChampionUseCase @Inject constructor(private val championsRepo: ChampionsRepo) {
    suspend operator fun invoke(champName: String) = championsRepo.getChampion(champName)
}