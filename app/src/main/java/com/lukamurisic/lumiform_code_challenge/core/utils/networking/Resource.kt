package com.lukamurisic.lumiform_code_challenge.core.utils.networking

typealias DomainError = Error

sealed interface Resource<out D, out E : Error> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Error<out E : DomainError>(val error: E) :
        Resource<Nothing, E>
}