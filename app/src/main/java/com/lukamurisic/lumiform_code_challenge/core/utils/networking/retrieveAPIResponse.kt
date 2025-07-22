package com.lukamurisic.lumiform_code_challenge.core.utils.networking

import kotlinx.coroutines.ensureActive
import retrofit2.Response
import timber.log.Timber
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> retrieveAPIResponse(
    execute: () -> Response<T>
): Resource<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        Timber.e("NO INTERNET")
        return Resource.Error(NetworkError.NO_INTERNET)
    }
    catch (e: UnknownHostException) {
        Timber.e("NO INTERNET")
        return Resource.Error(NetworkError.NO_INTERNET)
    } catch (e: Exception) {
        println("Error $e")
        coroutineContext.ensureActive()
        return Resource.Error(NetworkError.UNKNOWN)
    }

    return responseToResource(response)
}