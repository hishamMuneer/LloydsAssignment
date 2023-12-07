package com.lloyds.data.repository

import com.lloyds.data.repository.source.ChampDataSource
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.repository.ChampionsRepo
import com.lloyds.domain.shared.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ChampionsRepoImplTest {

    private lateinit var champDataSource: ChampDataSource
    private lateinit var championsRepo: ChampionsRepo

    @Before
    fun setup() {
        champDataSource = mockk()
        championsRepo = ChampionsRepoImpl(champDataSource)
    }

    @Test
    fun getChampionMap_SuccessfulResponse_ReturnsSuccessResult() = runTest {
        // Given
        val expectedResult = Result.Success(createChampionMap())
        coEvery { champDataSource.getChampionMap() } returns flowOf(expectedResult)

        // When
        val result = championsRepo.getChampionMap().toList()

        // Then
        coVerify { champDataSource.getChampionMap() }
        assertEquals(1, result.size)
        assertEquals(expectedResult, result.first())
    }

    @Test
    fun getChampionMap_FailedResponse_ReturnsErrorResult() = runTest {
        // Given
        val expectedResult = Result.Error(IllegalArgumentException("response failed"))
        coEvery { champDataSource.getChampionMap() } returns flowOf(expectedResult)

        // When
        val result = championsRepo.getChampionMap().toList()

        // Then
        coVerify { champDataSource.getChampionMap() }
        assertEquals(1, result.size)
        assertEquals(expectedResult, result.first())
    }

    @Test
    fun getChampion_SuccessfulResponse_ReturnsSuccessResult() = runTest {
        // Given
        val expectedResult = Result.Success(createChampion())
        coEvery { champDataSource.getChampion(any()) } returns flowOf(expectedResult)

        // When
        val result = championsRepo.getChampion("Aatrox").toList()

        // Then
        coVerify { champDataSource.getChampion("Aatrox") }
        assertEquals(1, result.size)
        assertEquals(expectedResult, result.first())
    }

    @Test
    fun getChampion_FailedResponse_ReturnsErrorResult() = runTest {
        // Given
        val expectedResult = Result.Error(IllegalArgumentException("response failed"))
        coEvery { champDataSource.getChampion(any()) } returns flowOf(expectedResult)

        // When
        val result = championsRepo.getChampion("Aatrox").toList()

        // Then
        coVerify { champDataSource.getChampion("Aatrox") }
        assertEquals(1, result.size)
        assertEquals(expectedResult, result.first())
    }

    private fun createChampionMap(): ChampionMap {
        return ChampionMap(data = mapOf("Aatrox" to createChampion()))
    }

    private fun createChampion(): Champion {
        return Champion(
            id = "Aatrox",
            name = "Aatrox",
            title = "The Darkin Blade",
            blurb = "Blurb here",
            lore = "Lore here",
            tags = listOf("Fighter", "Tank"),
            image = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg"
        )
    }
}