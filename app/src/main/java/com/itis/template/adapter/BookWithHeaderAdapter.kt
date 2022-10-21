package com.itis.template.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.template.R
import com.itis.template.model.MainItem

class BookWithHeaderAdapter(
    private var list: List<MainItem>,
    private val glide: RequestManager,
    private val action: (MainItem.BookUiModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        R.layout.item_title -> TitleItem.create(parent)
        R.layout.item_book -> BookItem.create(parent, glide, action)
        else -> throw IllegalStateException("Don't implement view type")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (val item = list[position]) {
            is MainItem.Title -> (holder as TitleItem).onBind(title = item.title)
            is MainItem.BookUiModel -> (holder as BookItem).onBind(item)
        }

//        when (holder) {
//            is TitleItem ->
//                holder.onBind((list[position] as MainItem.Title).title)
//            is BookItem ->
//                holder.onBind((list[position] as MainItem.BookUiModel))
//            else -> {
//            }
//    }


//        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long {
        return when (val item = list[position]) {
            is MainItem.BookUiModel -> item.id
            is MainItem.Title -> super.getItemId(position)
        }
    }

    fun updateDataSet(new: List<MainItem>) {
        list = new
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is MainItem.Title -> R.layout.item_title
            is MainItem.BookUiModel -> R.layout.item_book
        }
    }
}
