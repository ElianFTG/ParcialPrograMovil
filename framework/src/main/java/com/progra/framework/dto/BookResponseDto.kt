package com.progra.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BookResponseDto(
    @Json(name = "docs")
    val results: List<BookDto>
)