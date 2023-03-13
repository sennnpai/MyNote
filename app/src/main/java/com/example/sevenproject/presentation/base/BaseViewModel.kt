package com.example.sevenproject.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenproject.domain.utils.ResultStatus
import com.example.sevenproject.presentation.notes.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {



    protected fun <T> kotlinx.coroutines.flow.Flow<ResultStatus<T>>.collectFlow(state: MutableStateFlow<UiState<T>>) {
        viewModelScope.launch {
            this@collectFlow.collect {
                when (it) {
                    is ResultStatus.Error -> state.value = UiState.Error(it.error!!)
                    is ResultStatus.Loading -> state.value = UiState.Loding()
                    is ResultStatus.Success -> state.value = UiState.Success(it.data)
                }
            }

        }

    }
}

