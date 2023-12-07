package com.lloyds.domain.usecase

import com.lloyds.domain.repository.ChampionsRepo
import javax.inject.Inject

class ChampionUseCase @Inject constructor(private val championsRepo: ChampionsRepo) {
    suspend operator fun invoke(id: String) = championsRepo.getChampion(id)
}