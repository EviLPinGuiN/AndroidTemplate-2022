package com.itis.template.model

data class Book(
    val id: Int,
    val name: String,
    val author: String,
    val cover: String,
    val isFavorite: Boolean = false,
)
