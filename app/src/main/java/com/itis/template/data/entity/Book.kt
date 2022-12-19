package com.itis.template.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey
    val id: Int,
    val name: String,
    val year: String?,
    @ColumnInfo(name = "author_id")
    val authorId: String
)
