package com.lloyds.feature.championdetail.viewmodel

import com.lloyds.domain.shared.Result
import com.lloyds.domain.usecase.ChampionUseCase
import com.lloyds.feature.AATROX
import com.lloyds.feature.createChampion
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
class ChampionDetailViewModelTest() {

    private val championUseCase: ChampionUseCase = mockk()
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
    fun `test get champion detail success`() = runTest {
        // Given
        val expectedResult = Result.Success(createChampion())
        val id = AATROX
        coEvery { championUseCase(id) } returns flowOf(expectedResult)

        // When
        val viewModel = ChampionDetailViewModel(championUseCase)
        viewModel.getChampion(id)

        // Then
        val state = viewModel.championStateFlow.value
        assert(state is ViewState.Success)
        assertEquals(id, (state as ViewState.Success).data.id)
        assert(state.data.name == AATROX)
    }


    @Test
    fun `test get champion detail failure`() = runTest {

        // Given
        val expectedResult = Result.Error(IllegalStateException("Error"))
        val id = AATROX
        coEvery { championUseCase(id) } returns flowOf(expectedResult)

        // When
        val viewModel = ChampionDetailViewModel(championUseCase)
        viewModel.getChampion(id)

        // Then
        val state = viewModel.championStateFlow.value
        assert(state is ViewState.Error)
        assertEquals(expectedResult.exception.message, (state as ViewState.Error).message)
        assert(state.message == "Error")

    }
}