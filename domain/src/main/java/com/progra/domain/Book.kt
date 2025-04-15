package com.progra.domain

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val authors: List<String>,
    val yearPublish: Int,
)
