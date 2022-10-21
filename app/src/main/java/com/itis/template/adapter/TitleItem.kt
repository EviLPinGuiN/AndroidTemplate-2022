package com.itis.template.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.databinding.ItemTitleBinding

class TitleItem(
    private val binding: ItemTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(title: String) {
        binding.root.text = title
    }

    companion object {

        fun create(parent: ViewGroup): TitleItem = TitleItem(
            binding = ItemTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}