package com.itis.template.model

import androidx.annotation.ColorRes
import androidx.annotation.FontRes

sealed interface MainItem {

    data class Title(val title: String) : MainItem

    data class BookUiModel(
        val id: Long,
        val name: String,
        val author: String,
        val cover: String,
        val isFavorite: Boolean,
        @ColorRes val titleColor: Int,
        @FontRes val font: Int
    ) : MainItem
}
