package com.lloyds.feature.champions.viewmodel

import com.lloyds.domain.shared.Result
import com.lloyds.domain.usecase.ChampionMapUseCase
import com.lloyds.feature.createMultipleChampionMap
import com.lloyds.feature.state.ViewState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChampionListViewModelTest {

    private val championMapUseCase: ChampionMapUseCase = mockk()
    private lateinit var testDispatcher: TestDispatcher

    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Isolation between Tests & Avoid Leaking State
        Dispatchers.resetMain()
        clearAllMocks()
    }


    @Test
    fun `test get champion list failure`() = runTest {
        // Given
        val expectedError = Result.Error(IllegalArgumentException("some dumbo error happened"))
        coEvery { championMapUseCase() } returns flowOf(expectedError)

        // When
        val viewModel = ChampionListViewModel(championMapUseCase)
        viewModel.getChampions()

        // Then
        val result = viewModel.championStateFlow.value
        assert(result is ViewState.Error)
        assertEquals(expectedError.exception.message, (result as ViewState.Error).message)
        assert(result.message == "some dumbo error happened")
    }

    @Test
    fun `test get champion list success`() = runTest {

        // Given
        val expectedResult = Result.Success(createMultipleChampionMap())
        coEvery { championMapUseCase() } returns flowOf(expectedResult)

        // When
        val viewModel = ChampionListViewModel(championMapUseCase)
        viewModel.getChampions()

        // Then
        val state = viewModel.championStateFlow.value
        assert(state is ViewState.Success)
        assertEquals(expectedResult.data.champMap.values.toList(), (state as ViewState.Success).data)
        assert(state.data.size == 2)
    }
}