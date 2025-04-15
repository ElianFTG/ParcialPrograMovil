package com.progra.usecases

import com.progra.data.BookRepository
import com.progra.data.NetworkResult
import com.progra.domain.Book

class FindBooks(val bookrepository: BookRepository) {
    suspend fun invoke(title : String) : NetworkResult<List<Book>>{
        return bookrepository.getRemoteBooks(title)
    }
}