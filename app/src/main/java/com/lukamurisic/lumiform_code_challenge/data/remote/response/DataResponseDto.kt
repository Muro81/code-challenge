package com.lukamurisic.lumiform_code_challenge.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponseDto(
    @Json(name = "type") val type : String? = null,
    @Json(name = "title") val title : String? = null,
    @Json(name = "src") val src : String? = null,
    @Json(name = "items") val items : List<DataResponseDto>? = null
)
