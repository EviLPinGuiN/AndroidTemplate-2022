package com.itis.template.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.template.model.MainItem

class BookAdapter(
    private val list: List<MainItem.BookUiModel>,
    private val glide: RequestManager,
    private val action: (MainItem.BookUiModel) -> Unit,
) : RecyclerView.Adapter<BookItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookItem = BookItem.create(parent, glide, action)

    override fun onBindViewHolder(
        holder: BookItem,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun onBindViewHolder(
        holder: BookItem,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(list[position], payloads)
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateDataset() {

        notifyItemChanged(3, true)
    }
}