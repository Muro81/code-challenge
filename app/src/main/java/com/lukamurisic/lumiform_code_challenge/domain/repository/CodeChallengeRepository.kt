package com.lukamurisic.lumiform_code_challenge.domain.repository

import com.lukamurisic.lumiform_code_challenge.core.utils.networking.NetworkError
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.Resource
import com.lukamurisic.lumiform_code_challenge.domain.model.Page

interface CodeChallengeRepository {
    suspend fun getDataRemote(): Resource<List<Page>, NetworkError>
    suspend fun getDataLocal(): List<Page>
    suspend fun insertPages(pages : List<Page>) : Boolean
}