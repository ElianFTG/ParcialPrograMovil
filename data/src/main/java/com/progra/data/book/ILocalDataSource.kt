package com.progra.data.book

import com.progra.domain.Book

interface ILocalDataSource {
    suspend fun save(book: Book): Boolean
    suspend fun getAllBooksLike() : List<Book>
}