package com.lukamurisic.lumiform_code_challenge.core.utils.networking

import kotlinx.coroutines.ensureActive
import retrofit2.Response
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> retrieveAPIResponse(
    execute: () -> Response<T>
): Resource<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Resource.Error(NetworkError.NO_INTERNET)
    }  catch (e: Exception) {
        println("Error $e")
        coroutineContext.ensureActive()
        return Resource.Error(NetworkError.UNKNOWN)
    }

    return responseToResource(response)
}