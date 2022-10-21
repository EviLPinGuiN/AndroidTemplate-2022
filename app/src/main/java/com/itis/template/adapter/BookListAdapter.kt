package com.itis.template.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.template.model.MainItem

class BookListAdapter(
    private val glide: RequestManager,
    private val action: (MainItem.BookUiModel) -> Unit,
) : ListAdapter<MainItem.BookUiModel, BookItem>(
    object : DiffUtil.ItemCallback<MainItem.BookUiModel>() {
    override fun areItemsTheSame(
        oldItem: MainItem.BookUiModel,
        newItem: MainItem.BookUiModel
    ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MainItem.BookUiModel,
            newItem: MainItem.BookUiModel
        ): Boolean = oldItem == newItem

    override fun getChangePayload(
        oldItem: MainItem.BookUiModel,
        newItem: MainItem.BookUiModel
    ): Any? {
        val bundle = Bundle()
        if (oldItem.name != newItem.name) {
            bundle.putString(BookItem.ARG_NAME, newItem.name)
        }
        if (oldItem.isFavorite != newItem.isFavorite) {
            bundle.putBoolean(BookItem.ARG_FAVORITE, newItem.isFavorite)
        }
        return if (bundle.isEmpty) super.getChangePayload(oldItem, newItem) else bundle
    }
}
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookItem = BookItem.create(parent, glide, action)

    override fun onBindViewHolder(
        holder: BookItem,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: BookItem,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.updateFromBundle(payloads.last() as? Bundle)
        }
    }

}