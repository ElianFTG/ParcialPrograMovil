package com.progra.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BookDto(
    @Json(name= "author_name")
    val authors: List<String>,
    @Json(name= "first_publish_year")
    val yearPublish: Int,
    @Json(name= "title")
    val title: String
)