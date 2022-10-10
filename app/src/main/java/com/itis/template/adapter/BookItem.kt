package com.itis.template.adapter

import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.template.R
import com.itis.template.databinding.ItemBookBinding
import com.itis.template.model.BookUiModel

class BookItem(
    private val binding: ItemBookBinding,
    private val glide: RequestManager,
    private val action: (BookUiModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var book: BookUiModel? = null

    private val option = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    init {
        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            book?.also(action)
        }
    }

    fun onBind(book: BookUiModel) {
        with(binding) {
            tvTitle.text = book.name
            tvDesc.text = book.author

            tvTitle.setTextColor(
                itemView.context.getColor(book.titleColor)
            )

            tvTitle.typeface = ResourcesCompat.getFont(itemView.context, book.font)
            /* bad way
            if (book.name == "Naruto") {
                tvTitle.setTextColor(
                    itemView.context.getColor(R.color.purple_700)
                )
            } else {
                tvTitle.setTextColor(
                    itemView.context.getColor(R.color.black)
                )
            }
            */

            glide
                .load(book.cover)
                .apply(option)
                .placeholder(R.drawable.cote_error_2)
                .error(R.drawable.cote)
                .into(ivCover)
        }
    }

    fun updateCheck(isState: Boolean) {

    }
}