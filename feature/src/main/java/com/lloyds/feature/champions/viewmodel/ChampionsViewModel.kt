package com.lloyds.feature.champions.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyds.domain.usecase.ChampionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "Champions"
class ChampionsViewModel @Inject constructor(
    private val championUseCase: ChampionUseCase
) : ViewModel() {

    init {
        getChampions()
    }

    fun getChampions() {
        viewModelScope.launch {
            championUseCase().collect {
                it.data.forEach { (key, value) ->
                    Log.d(TAG, "key = $key || value : $value")
                }
            }
        }
    }
}