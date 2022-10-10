package com.itis.template.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.template.model.BookUiModel
import com.itis.template.databinding.ItemBookBinding

class BookAdapter(
    private val list: List<BookUiModel>,
    private val glide: RequestManager,
    private val action: (BookUiModel) -> Unit,
) : RecyclerView.Adapter<BookItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookItem = BookItem(
        binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        action = action
    )

    override fun onBindViewHolder(
        holder: BookItem,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateDataset() {

        notifyItemChanged(3, true)
    }
}