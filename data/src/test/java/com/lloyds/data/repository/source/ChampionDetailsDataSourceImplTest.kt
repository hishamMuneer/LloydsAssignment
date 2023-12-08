package com.lloyds.data.repository.source

import com.lloyds.data.BaseUnitTest
import com.lloyds.data.api.ChampionService
import com.lloyds.data.mapper.ChampionDetailApiToDomainMapper
import com.lloyds.domain.shared.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ChampionDetailsDataSourceImplTest: BaseUnitTest() {

    private lateinit var service: ChampionService
    private lateinit var championDetailApiMapper: ChampionDetailApiToDomainMapper
    private lateinit var dataSource: ChampionDetailsDataSource

    @Before
    fun setUp() {
        service = mockk()
        championDetailApiMapper = mockk()
        dataSource = ChampionDetailsDataSourceImpl(service, championDetailApiMapper)
    }

    @Test
    fun getChampion_SuccessfulResponse_ReturnsSuccessResult() = runTest {
        // Given
        coEvery { service.getChampion("Aatrox") } returns Response.success(createApiChampionDetails())
        coEvery { championDetailApiMapper.map(any()) } returns createChampion()

        // When
        val result = dataSource.getChampion("Aatrox").first()

        // Then
        assert(result is Result.Success)
    }

    @Test
    fun getChampion_FailedResponse_ReturnsErrorResult() = runTest {
        // Given
        coEvery { service.getChampion("Aatrox") } returns Response.error(400, emptyResponseBody())

        // When
        val result = dataSource.getChampion("Aatrox").first()

        // Then
        assert(result is Result.Error)
    }

    @Test
    fun getChampion_ExceptionThrown_ReturnsErrorResult() = runTest {
        // Given
        coEvery { service.getChampion("Aatrox") } throws RuntimeException("Some exception")

        // When
        val result = dataSource.getChampion("Aatrox").first()

        // Then
        assert(result is Result.Error)
    }

}