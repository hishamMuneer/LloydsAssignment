package com.lloyds.feature.championdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyds.domain.model.Champion
import com.lloyds.domain.shared.Result
import com.lloyds.domain.usecase.ChampionUseCase
import com.lloyds.feature.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailViewModel @Inject constructor(
    private val useCase: ChampionUseCase
) : ViewModel() {

    // backing property
    private val _championStateFlow: MutableStateFlow<ViewState<Champion>> =
        MutableStateFlow(ViewState.Loading)
    val championStateFlow: StateFlow<ViewState<Champion>>
        get() = _championStateFlow

    fun getChampion(champName: String) {
        viewModelScope.launch {
            useCase(champName).collect {
                when (it) {
                    is Result.Error -> {
                        _championStateFlow.value =
                            ViewState.Error(it.exception.message ?: "error loading")
                    }

                    is Result.Success -> {
                        _championStateFlow.value = ViewState.Success(it.data)
                    }
                }
            }
        }
    }
}