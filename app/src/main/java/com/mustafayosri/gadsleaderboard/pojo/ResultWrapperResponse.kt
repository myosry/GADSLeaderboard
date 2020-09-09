package com.mustafayosri.gadsleaderboard.pojo

sealed class ResultWrapperResponse<out T> {

    data class Success<out T>(val value: T?) : ResultWrapperResponse<T>()

    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapperResponse<Nothing>()

    object NetworkError : ResultWrapperResponse<Nothing>()
}