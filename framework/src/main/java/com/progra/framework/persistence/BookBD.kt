package com.progra.framework.persistence

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity ( tableName = "books")
data class BookBD(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "yearPublish")
    var yearPublish : Int,
    @ColumnInfo(name = "yearPublish")
    var authors : String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}