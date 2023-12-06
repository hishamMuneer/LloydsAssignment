package com.lloyds.feature.champions.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.lloyds.feature.champions.viewmodel.ChampionListViewModel
import com.lloyds.feature.shared.ui.ErrorView
import com.lloyds.feature.shared.ui.LoadingView
import com.lloyds.feature.state.ViewState

@Composable
fun ChampionListScreen(
    viewModel: ChampionListViewModel, selectedItem: (String) -> Unit
) {
    when (val result = viewModel.championStateFlow.collectAsState().value) {
        is ViewState.Loading -> {
            LoadingView()
        }

        is ViewState.Success -> {
            val list = result.data
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(list.size) {
                    ChampionGridCardUI(list[it].second, selectedItem)
                }
            }
        }

        is ViewState.Error -> ErrorView(reason = result.message)
    }
}

