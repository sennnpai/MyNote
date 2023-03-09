package com.example.sevenproject.domain.utils

import com.example.sevenproject.domain.model.Note

sealed class ResultStatus<T>(
    val data:T? = null,
   val error: String? = null

    ){
    class Loading<T> : ResultStatus<T>()
    class Success<T>(data: T?): ResultStatus<T>(data = data, error = null)
    class Error<T>(msg: String?): ResultStatus<T>(error = msg)
}
