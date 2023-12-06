package com.lloyds.feature.championdetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.lloyds.feature.championdetail.viewmodel.ChampionDetailViewModel
import com.lloyds.feature.state.ViewState
import com.lloyds.feature.shared.ui.ErrorView
import com.lloyds.feature.shared.ui.LoadingView

@Composable
fun ChampionDetailScreen(viewModel: ChampionDetailViewModel, champName: String) {

    LaunchedEffect(key1 = true) {
        viewModel.getChampion(champName)
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