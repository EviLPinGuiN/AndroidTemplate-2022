package com.itis.template.model

import androidx.annotation.ColorRes
import androidx.annotation.FontRes

data class BookUiModel(
    val id: Int,
    val name: String,
    val author: String,
    val cover: String,
    @ColorRes val titleColor: Int,
    @FontRes val font: Int
)
