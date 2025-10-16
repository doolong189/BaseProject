package dev.hoanglong180903.baseproject.core.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

abstract class BaseAdapter<T, VH: BaseViewHolder<T>>(diffUtil: DiffUtil.ItemCallback<T>): ListAdapter<T, VH>(
        AsyncDifferConfig.Builder(diffUtil)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ){
    private var recyclerView: RecyclerView? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindView(getItem(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<T>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }
}