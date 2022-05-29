package com.zhenl.violet.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by lin on 22-5-29.
 */
class RoundTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    init {
        setBackgroundDrawable(RoundDrawable.fromAttributeSet(context, attrs))
    }
}