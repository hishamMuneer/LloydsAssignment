package com.lloyds.feature.state

sealed class ViewState<out R> {
    data object Loading : ViewState<Nothing>()
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Error(val message: String) : ViewState<Nothing>()
}