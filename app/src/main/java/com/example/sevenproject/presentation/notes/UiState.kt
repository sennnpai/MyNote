package com.example.sevenproject.presentation.notes

sealed class UiState<T> {
    class Loding<T> : UiState<T>()
    class Success<T>(var data: T?) : UiState<T>()
    class Error<T>(val msg: String) : UiState<T>()
    class Empty<T>: UiState<T>()
}