package com.zhenl.violet.base

import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * Created by lin on 2022/7/9.
 */
open class SimplePagingDataAdapter<T : Any, VH : BaseViewHolder<T>>(
    private val creator: (ViewGroup) -> VH,
    diffCallback: DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return true
        }
    }
) : PagingDataAdapter<T, VH>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return creator(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.item = getItem(position)
        holder.bind(holder.item)
    }

    fun getDefItem(@IntRange(from = 0) position: Int): T? {
        return super.getItem(position)
    }
}