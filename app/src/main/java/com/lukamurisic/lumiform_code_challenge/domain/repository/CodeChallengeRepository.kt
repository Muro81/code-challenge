package com.lukamurisic.lumiform_code_challenge.domain.repository

import com.lukamurisic.lumiform_code_challenge.core.utils.networking.NetworkError
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.Resource
import com.lukamurisic.lumiform_code_challenge.domain.model.Page

interface CodeChallengeRepository {
    suspend fun getData() : Resource<List<Page>, NetworkError>
}