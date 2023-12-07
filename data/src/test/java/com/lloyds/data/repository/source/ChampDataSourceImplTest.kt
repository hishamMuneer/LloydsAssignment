package com.lloyds.data.repository.source

import com.lloyds.data.BaseUnitTest
import com.lloyds.data.api.ChampionService
import com.lloyds.data.mapper.ChampionDetailApiToDomainMapper
import com.lloyds.data.mapper.ChampionListApiToDomainMapper
import com.lloyds.data.model.APIChampion
import com.lloyds.data.model.APIChampionDetails
import com.lloyds.data.model.APIChampionMap
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.shared.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After

import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ChampDataSourceImplTest: BaseUnitTest() {

    private val service: ChampionService = mockk()
    private val championListApiMapper: ChampionListApiToDomainMapper = mockk()
    private val championDetailApiMapper: ChampionDetailApiToDomainMapper = mockk()
    private lateinit var dataSource: ChampDataSource

    @Before
    fun setUp() {
        dataSource = ChampDataSourceImpl(service, championListApiMapper, championDetailApiMapper)
    }

    @Test
    fun getChampionMap_SuccessfulResponse_ReturnsSuccessResult(): Unit = runTest {
        // Given
        coEvery { service.getChampionList() } returns Response.success(createApiChampionMap())
        coEvery { championListApiMapper.map(any()) } returns createChampionMap()

        // When
        val result = dataSource.getChampionMap().first()

        // Then
        assert(result is Result.Success)
    }

    @Test
    fun getChampionMap_FailedResponse_ReturnsErrorResult() = runTest {
        // Given
        coEvery { service.getChampionList() } returns Response.error(400, emptyResponseBody())

        // When
        val result = dataSource.getChampionMap().first()

        // Then
        assert(result is Result.Error)
    }

    @Test
    fun getChampionMap_ExceptionThrown_ReturnsErrorResult() = runTest {
        // Given
        coEvery { service.getChampionList() } throws RuntimeException("Some exception")

        // When
        val result = dataSource.getChampionMap().first()

        // Then
        assert(result is Result.Error)
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