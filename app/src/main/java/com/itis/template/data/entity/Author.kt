package com.itis.template.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
data class Author(
    @PrimaryKey
    val id: Int,
    val name: String,
)
