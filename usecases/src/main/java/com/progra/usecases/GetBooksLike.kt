package com.progra.usecases

import com.progra.data.BookRepository
import com.progra.domain.Book

class GetBooksLike(val bookrepository: BookRepository) {
    suspend fun invoke() : List<Book> {
        return this.bookrepository.getAllBooksLike()
    }
}