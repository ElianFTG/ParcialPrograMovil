package com.progra.data

import com.progra.domain.Book
import com.progra.data.book.IBookRemoteDataSource
import com.progra.data.book.ILocalDataSource

class BookRepository(
    val remoteDataSource: IBookRemoteDataSource,
    val localDataSource: ILocalDataSource
) {
    suspend fun getRemoteBooks(title: String): NetworkResult<List<Book>> {
        return  this.remoteDataSource.fetchBooks(title)
    }

    suspend fun save(book: Book): Boolean {
        return this.localDataSource.save(book)
    }

    suspend fun getAllBooksLike(): List<Book>{
        return this.localDataSource.getAllBooksLike()
    }
}