package com.zhenl.violet.ktx

import android.content.res.Resources
import kotlin.math.ceil
import kotlin.math.roundToInt

/**
 * Created by lin on 2020/10/3.
 */

fun Int.toDp(): Int = ceil(this / Resources.getSystem().displayMetrics.density).roundToInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).roundToInt()