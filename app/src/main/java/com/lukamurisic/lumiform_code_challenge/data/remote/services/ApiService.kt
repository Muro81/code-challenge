package com.lukamurisic.lumiform_code_challenge.data.remote.services

import com.lukamurisic.lumiform_code_challenge.data.remote.response.DataResponseDto
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("f118b9f0-6f84-435e-85d5-faf4453eb72a")
    suspend fun getData() : Response<DataResponseDto>
}