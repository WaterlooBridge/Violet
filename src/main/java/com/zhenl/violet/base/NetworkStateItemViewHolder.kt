package com.zhenl.violet.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.zhenl.violet.R

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class NetworkStateItemViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.brvah_quick_view_load_more, parent, false)
) {
    private val loadingView = itemView.findViewById<View>(R.id.load_more_loading_view)
    private val loadComplete = itemView.findViewById<View>(R.id.load_more_load_complete_view)
    private val loadEndView = itemView.findViewById<View>(R.id.load_more_load_end_view)
    private val loadFailView = itemView.findViewById<View>(R.id.load_more_load_fail_view)
        .also {
            it.setOnClickListener { retryCallback() }
        }

    fun bindTo(loadState: LoadState) {
        when (loadState) {
            is LoadState.NotLoading -> {
                loadingView.isVisible = false
                loadFailView.isVisible = false
                loadComplete.isVisible = !loadState.endOfPaginationReached
                loadEndView.isVisible = loadState.endOfPaginationReached
            }
            is LoadState.Loading -> {
                loadingView.isVisible = true
                loadComplete.isVisible = false
                loadFailView.isVisible = false
                loadEndView.isVisible = false
            }
            is LoadState.Error -> {
                loadingView.isVisible = false
                loadComplete.isVisible = false
                loadFailView.isVisible = true
                loadEndView.isVisible = false
            }
        }
    }
}