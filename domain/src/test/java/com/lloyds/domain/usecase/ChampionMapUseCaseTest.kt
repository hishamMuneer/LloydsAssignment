package com.lloyds.domain.usecase

import com.lloyds.domain.BaseUnitTest
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.repository.ChampionsRepo
import com.lloyds.domain.shared.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ChampionMapUseCaseTest : BaseUnitTest() {

    private val championsRepo = mockk<ChampionsRepo>()
    private lateinit var championMapUseCase: ChampionMapUseCase

    @Before
    fun setUp() {
        championMapUseCase = ChampionMapUseCase(championsRepo)
    }

    @Test
    fun `invoke with Success Response`() = runTest {
        // Given
        val expectedResult = Result.Success(createChampionMap())
        coEvery { championsRepo.getChampionMap() } returns flowOf(expectedResult)

        // When
        val result = mutableListOf<Result<ChampionMap>>()
        championMapUseCase().collect {
            result.add(it)
        }

        // Then
        assertEquals(listOf(expectedResult), result)
    }

    @Test
    fun `invoke with Error Response`() = runTest {

        // Given
        val expectedResult = Result.Error(IllegalArgumentException("response failed"))
        coEvery { championsRepo.getChampionMap() } returns flowOf(expectedResult)

        // When
        val result = mutableListOf<Result<ChampionMap>>()
        championMapUseCase().collect {
            result.add(it)
        }

        // Then
        assertEquals(listOf(expectedResult), result)
    }

}