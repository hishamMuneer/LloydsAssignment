package com.lloyds.domain.usecase

import com.lloyds.domain.BaseUnitTest
import com.lloyds.domain.model.Champion
import com.lloyds.domain.repository.ChampionsRepo
import com.lloyds.domain.shared.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ChampionUseCaseTest: BaseUnitTest() {

    private val championsRepo = mockk<ChampionsRepo>()
    private lateinit var championUseCase: ChampionUseCase

    @Before
    fun setUp() {
        championUseCase = ChampionUseCase(championsRepo)
    }

    @Test
    fun `invoke with Success Response`() = runTest {
        // Given
        val championId = "Aatrox"

        val expectedResult = Result.Success(createChampion())
        coEvery { championsRepo.getChampion(championId) } returns flow { emit(expectedResult) }

        // When
        val result = mutableListOf<Result<Champion>>()
        championUseCase.invoke(championId).collect {
            result.add(it)
        }

        // Then
        assertEquals(listOf(expectedResult), result)
    }

    @Test
    fun `invoke with Error Response`() = runTest {
        // Given
        val championId = "NonExistentChampion"

        val expectedResult = Result.Error(IllegalArgumentException("response failed"))
        coEvery { championsRepo.getChampion(championId) } returns flow { emit(expectedResult) }

        // When
        val result = mutableListOf<Result<Champion>>()
        championUseCase.invoke(championId).collect {
            result.add(it)
        }

        // Then
        assertEquals(listOf(expectedResult), result)
    }
}