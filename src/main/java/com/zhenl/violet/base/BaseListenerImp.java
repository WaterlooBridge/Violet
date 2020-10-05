package com.zhenl.violet.base;

import androidx.annotation.Nullable;

import com.zhenl.violet.base.listener.GridSpanSizeLookup;
import com.zhenl.violet.base.listener.OnItemChildClickListener;
import com.zhenl.violet.base.listener.OnItemChildLongClickListener;
import com.zhenl.violet.base.listener.OnItemClickListener;
import com.zhenl.violet.base.listener.OnItemLongClickListener;

/**
 * Created by lin on 2020/10/3.
 */
public interface BaseListenerImp {
    /**
     * Register a callback to be invoked when an item in this RecyclerView has
     * been clicked.
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemClickListener(@Nullable OnItemClickListener listener);

    void setOnItemLongClickListener(@Nullable OnItemLongClickListener listener);

    void setOnItemChildClickListener(@Nullable OnItemChildClickListener listener);

    void setOnItemChildLongClickListener(@Nullable OnItemChildLongClickListener listener);

    void setGridSpanSizeLookup(@Nullable GridSpanSizeLookup spanSizeLookup);
}
