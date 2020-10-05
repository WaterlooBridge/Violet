package com.zhenl.violet.base.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

/**
 * Created by lin on 2020/10/3.
 */
public interface GridSpanSizeLookup {

    int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position);
}
