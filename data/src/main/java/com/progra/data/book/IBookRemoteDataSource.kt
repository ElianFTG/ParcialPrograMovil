package com.progra.data.book

import com.progra.data.NetworkResult
import com.progra.domain.Book

interface IBookRemoteDataSource {
    suspend fun fetchBooks(title: String) : NetworkResult<List<Book>>
}