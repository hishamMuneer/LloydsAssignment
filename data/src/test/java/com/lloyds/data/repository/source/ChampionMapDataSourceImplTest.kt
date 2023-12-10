package com.lloyds.data.repository.source

import com.lloyds.data.BaseUnitTest
import com.lloyds.data.api.ChampionService
import com.lloyds.data.mapper.ChampionListApiToDomainMapper
import com.lloyds.data.repository.ApiResponseHandler
import com.lloyds.domain.shared.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ChampionMapDataSourceImplTest: BaseUnitTest() {

    private lateinit var service: ChampionService
    private lateinit var championListApiMapper: ChampionListApiToDomainMapper
    private lateinit var apiResponseHandler: ApiResponseHandler
    private lateinit var dataSource: ChampionMapDataSource

    @Before
    fun setUp() {
        service = mockk()
        championListApiMapper = mockk()
        apiResponseHandler = ApiResponseHandler()
        dataSource = ChampionMapDataSourceImpl(service, championListApiMapper, apiResponseHandler)
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

}