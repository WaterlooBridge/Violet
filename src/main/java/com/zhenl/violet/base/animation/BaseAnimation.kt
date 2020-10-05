package com.zhenl.violet.base.animation

import android.animation.Animator
import android.view.View

/**
 * Created by lin on 2020/10/3.
 */
interface BaseAnimation {
    fun animators(view: View): Array<Animator>
}