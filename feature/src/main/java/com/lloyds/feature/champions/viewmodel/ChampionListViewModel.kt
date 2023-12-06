package com.lloyds.feature.champions.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyds.domain.model.Champion
import com.lloyds.domain.shared.Result
import com.lloyds.domain.usecase.ChampionMapUseCase
import com.lloyds.feature.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "Champions"

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val championsUseCase: ChampionMapUseCase
) : ViewModel() {

    // backing property
    private val _championStateFlow: MutableStateFlow<ViewState<List<Pair<String, Champion>>>> =
        MutableStateFlow(ViewState.Loading)
    val championStateFlow: StateFlow<ViewState<List<Pair<String, Champion>>>>
        get() = _championStateFlow

    init {
        getChampions()
    }

    fun getChampions() {
        viewModelScope.launch {
            championsUseCase().collect {
                when (it) {
                    is Result.Error -> {
                        _championStateFlow.value =
                            ViewState.Error(it.exception.message ?: "error loading")
                    }

                    is Result.Success -> {
                        val champList = it.data.data.toList()
                        _championStateFlow.value = ViewState.Success(champList)
                        champList.forEach { (key, value) ->
                            Log.d(TAG, "key = $key || value : $value")
                        }
                    }
                }
            }
        }
    }
}