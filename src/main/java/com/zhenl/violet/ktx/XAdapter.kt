package com.zhenl.violet.ktx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ConcatAdapter
import com.zhenl.violet.base.SimpleLoadStateAdapter
import com.zhenl.violet.base.SimplePagingDataAdapter

/**
 * Created by lin on 2020/10/3.
 */

fun ViewGroup.getItemView(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutResId, this, false)
}

fun SimplePagingDataAdapter<*, *>.withNetworkLoadStateFooter(): ConcatAdapter {
    return this.withLoadStateFooter(SimpleLoadStateAdapter(this))
}