package com.zhenl.violet.base.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.zhenl.violet.base.BasePagedListAdapter;

/**
 * Created by lin on 2020/10/3.
 */
public interface OnItemClickListener {
    /**
     * Callback method to be invoked when an item in this RecyclerView has
     * been clicked.
     *
     * @param adapter  the adapter
     * @param view     The itemView within the RecyclerView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     */
    void onItemClick(@NonNull BasePagedListAdapter<?> adapter, @NonNull View view, int position);
}
