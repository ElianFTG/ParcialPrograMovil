package com.progra.framework.book

import android.content.Context
import com.progra.data.book.ILocalDataSource
import com.progra.domain.Book
import com.progra.framework.mappers.toEntity
import com.progra.framework.mappers.toModel
import com.progra.framework.persistence.AppRoomDatabase
import com.progra.framework.persistence.IBookDAO

class BookLocalDataSource(val context: Context) : ILocalDataSource {
    val bookDAO : IBookDAO = AppRoomDatabase.getDatabase(context).bookDao()
    override suspend fun save(book: Book): Boolean {
        bookDAO.insert(book.toEntity())
        return true
    }

    override suspend fun getAllBooksLike(): List<Book> {
        return bookDAO.getBooks().map { it.toModel() }
    }
}