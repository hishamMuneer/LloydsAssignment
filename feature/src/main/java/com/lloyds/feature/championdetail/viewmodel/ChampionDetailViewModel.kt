package com.lloyds.feature.championdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyds.domain.model.Champion
import com.lloyds.domain.shared.Result
import com.lloyds.domain.usecase.ChampionUseCase
import com.lloyds.feature.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChampionDetailViewModel @Inject constructor(
    private val championUseCase: ChampionUseCase
) : ViewModel() {

    // backing property
    private val _championStateFlow: MutableStateFlow<ViewState<Champion>> =
        MutableStateFlow(ViewState.Loading)
    val championStateFlow = _championStateFlow.asStateFlow()

    fun getChampion(id: String) {
        viewModelScope.launch {
            _championStateFlow.value = ViewState.Loading
            championUseCase(id).collect {
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