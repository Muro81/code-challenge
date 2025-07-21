package com.lukamurisic.lumiform_code_challenge.data.repository

import com.lukamurisic.lumiform_code_challenge.core.utils.networking.NetworkError
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.Resource
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.map
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.retrieveAPIResponse
import com.lukamurisic.lumiform_code_challenge.data.remote.mappers.toPagesList
import com.lukamurisic.lumiform_code_challenge.data.remote.response.DataResponseDto
import com.lukamurisic.lumiform_code_challenge.data.remote.services.ApiService
import com.lukamurisic.lumiform_code_challenge.domain.model.Page
import com.lukamurisic.lumiform_code_challenge.domain.repository.CodeChallengeRepository
import javax.inject.Inject

class CodeChallengeRepositoryImpl@Inject constructor(private val apiService: ApiService): CodeChallengeRepository{
    override suspend fun getData(): Resource<List<Page>, NetworkError> {
        return retrieveAPIResponse<DataResponseDto> {
            apiService.getData()
        }.map {
            it.toPagesList()
        }
    }

}