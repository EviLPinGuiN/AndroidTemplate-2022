package com.itis.template.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.template.R
import com.itis.template.databinding.ItemBookBinding
import com.itis.template.model.MainItem

class BookItem(
    private val binding: ItemBookBinding,
    private val glide: RequestManager,
    private val action: (MainItem.BookUiModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var book: MainItem.BookUiModel? = null

    private val option = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    init {
        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            book?.also(action)
        }
    }

    fun onBind(book: MainItem.BookUiModel) {
        this.book = book
        with(binding) {
            tvTitle.text = book.name
            tvDesc.text = book.author

            tvTitle.setTextColor(
                itemView.context.getColor(book.titleColor)
            )

            tvTitle.typeface = ResourcesCompat.getFont(itemView.context, book.font)

            ivMood.setChecked(book.isFavorite)

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

    fun onBind(
        book: MainItem.BookUiModel,
        payloads: MutableList<Any>
    ) {
        (payloads.last() as? Bundle)?.also { bundle ->
            updateFromBundle(bundle)
        }
    }

    fun onBind(
        book: MainItem.BookUiModel,
        bundle: Bundle
    ) {
        updateFromBundle(bundle)
    }

    fun updateFromBundle(bundle: Bundle?) {
        if (bundle?.containsKey(ARG_FAVORITE) == true) {
            bundle.getBoolean(ARG_FAVORITE).also {
                binding.ivMood.setChecked(it)
            }
        }
        if (bundle?.containsKey(ARG_NAME) == true) {
            bundle.getString(ARG_NAME).also {
                binding.tvTitle.text = it
            }
        }
    }

    private fun ImageView.setChecked(isCheck: Boolean) {
        setImageResource(
            if (isCheck)
                R.drawable.ic_baseline_mood
            else
                R.drawable.ic_baseline_mood_bad
        )
    }

    companion object {

        const val ARG_FAVORITE = "arg_favorite"
        const val ARG_NAME = "arg_name"

        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (MainItem.BookUiModel) -> Unit,
        ): BookItem = BookItem(
            binding = ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide = glide,
            action = action
        )
    }
}