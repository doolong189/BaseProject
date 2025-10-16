package dev.hoanglong180903.baseproject.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(
    binding: ViewBinding
): RecyclerView.ViewHolder(binding.root) {
    private var item: T? = null
    open fun bindView(item: T){
        this.item = item
    }
}