package com.progra.framework.mappers

import com.progra.domain.Book
import com.progra.framework.dto.BookResponseDto
import com.progra.framework.dto.BookDto
import com.progra.framework.persistence.BookBD

fun Book.toEntity(): BookBD {
    return BookBD(
        title = title,
        authors = authors,
        yearPublish = yearPublish
    )
}

fun BookBD.toModel(): Book {
    return Book(
        title,
        authors,
        yearPublish
    )
}

fun BookResponseDto.toModel() : List<Book> {
    return results.map { it.toModel() }
}

fun BookDto.toModel(): Book {
    return Book(
        title = title,
        authors = authors,
        yearPublish = yearPublish
    )
}