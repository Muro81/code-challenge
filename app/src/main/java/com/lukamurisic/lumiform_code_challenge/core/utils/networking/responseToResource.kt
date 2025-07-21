package com.lukamurisic.lumiform_code_challenge.core.utils.networking

import retrofit2.Response

/**
This is the function we will use to turn the API response to our custom Resource
so we can handle it properly in the VMs.
 **/

/**
val errorBody -> response.errorBody() is the equivalent to response.body() in case of an error response
the .string() must be called so we get the actual value of the response, and not just
its memory address.
val errorMessage -> we need to map the information we get from our API error response
so it can visually be represented as it should. If we didn't do this,
instead of having a formatted error message, we would show the whole JSON of our
error response, instead of it's value
 **/

/**
reified keyword is used in inline functions to allow access to the actual type arguments at runtime
 **/

inline fun <reified T> responseToResource(response: Response<T>): Resource<T, NetworkError> {

    return when (response.code()) {
        in 200..299 -> {
            try {
                Resource.Success(response.body() as T)
            } catch (e: Exception) {
                Resource.Error(NetworkError.SERIALIZATION)
            }
        }

        400 -> Resource.Error(NetworkError.BAD_REQUEST)
        401 -> Resource.Error(NetworkError.UNAUTHORIZED)
        403 -> Resource.Error(NetworkError.FORBIDDEN)
        404 -> Resource.Error(NetworkError.NOT_FOUND)
        408 -> Resource.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Resource.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Resource.Error(NetworkError.SERVER_ERROR)
        else -> Resource.Error(NetworkError.UNKNOWN)
    }
}
