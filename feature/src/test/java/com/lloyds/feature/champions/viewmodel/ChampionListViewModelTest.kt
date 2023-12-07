package com.lloyds.feature.champions.viewmodel

import com.lloyds.domain.repository.ChampionsRepo
import com.lloyds.domain.usecase.ChampionMapUseCase
import com.lloyds.feature.BaseUnitTest
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ChampionListViewModelTest: BaseUnitTest() {

    private lateinit var viewModel: ChampionListViewModel
    private val championMapUseCase: ChampionMapUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = ChampionListViewModel(championMapUseCase)
    }

    @Test
    fun `test get champion list success`() {

        viewModel.getChampions()
    }
}