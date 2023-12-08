package com.lloyds.feature.championdetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.lloyds.feature.championdetail.viewmodel.ChampionDetailViewModel
import com.lloyds.feature.shared.ui.ErrorView
import com.lloyds.feature.shared.ui.LoadingView
import com.lloyds.feature.state.ViewState

@Composable
fun ChampionDetailScreen(viewModel: ChampionDetailViewModel, id: String) {

    LaunchedEffect(key1 = true) {
        viewModel.getChampion(id)
    }
    when (val champion = viewModel.championStateFlow.collectAsState().value) {
        is ViewState.Success -> {
            ChampionUI(champion = champion.data)
        }
        is ViewState.Loading -> {
            LoadingView()
        }
        is ViewState.Error -> {
            ErrorView(reason = champion.message)
        }
    }
}