package com.progra.usecases

import com.progra.data.BookRepository
import com.progra.domain.Book

class GetBooksLike(val bookrepository: BookRepository) {
    suspend fun getBooksLike() : List<Book> {
        return bookrepository.getAllBooksLike()
    }
}