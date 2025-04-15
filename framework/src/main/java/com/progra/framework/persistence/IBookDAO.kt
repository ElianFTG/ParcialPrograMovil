package com.progra.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IBookDAO {
    @Query("SELECT * FROM books")
    fun getBooks(): List<BookBD>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: BookBD)
}