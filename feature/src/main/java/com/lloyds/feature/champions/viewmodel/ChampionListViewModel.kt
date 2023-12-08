package com.lloyds.feature.champions.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyds.domain.model.Champion
import com.lloyds.domain.shared.Result
import com.lloyds.domain.usecase.ChampionMapUseCase
import com.lloyds.feature.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val championsUseCase: ChampionMapUseCase
) : ViewModel() {

    // backing property
    private val _championStateFlow: MutableStateFlow<ViewState<List<Champion>>> =
        MutableStateFlow(ViewState.Loading)
    val championStateFlow: StateFlow<ViewState<List<Champion>>>
        get() = _championStateFlow

    init {
        getChampions()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getChampions() {
        viewModelScope.launch {
            _championStateFlow.value = ViewState.Loading
            championsUseCase().collect {
                when (it) {
                    is Result.Error -> {
                        _championStateFlow.value =
                            ViewState.Error(it.exception.message ?: "error loading")
                    }

                    is Result.Success -> {
                        val champList = it.data.data.values.toList()
                        _championStateFlow.value = ViewState.Success(champList)
                    }
                }
            }
        }
    }
}