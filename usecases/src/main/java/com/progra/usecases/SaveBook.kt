package com.progra.usecases

import com.progra.data.BookRepository
import com.progra.domain.Book

class SaveBook(val repository: BookRepository) {
    suspend fun invoke(book: Book) : Boolean {
        return this.repository.save(book)
    }
}