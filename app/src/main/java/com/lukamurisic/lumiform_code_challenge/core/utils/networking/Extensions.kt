package com.lukamurisic.lumiform_code_challenge.core.utils.networking



inline fun <T, E: Error, R> Resource<T, E>.map(map: (T) -> R): Resource<R, E> {
    return when(this) {
        is Resource.Error -> Resource.Error(error)
        is Resource.Success -> Resource.Success(map(data))
    }
}

inline fun <T, E: Error> Resource<T, E>.onSuccess(action: (T) -> Unit): Resource<T, E> {
    return when(this) {
        is Resource.Error -> this
        is Resource.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: Error> Resource<T, E>.onError(action: (E) -> Unit): Resource<T, E> {
    return when(this) {
        is Resource.Error -> {
            action(error)
            this
        }
        is Resource.Success -> this
    }
}