package com.zhenl.violet.base.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator

/**
 * Created by lin on 2020/10/3.
 */
class SlideInBottomAnimation : BaseAnimation {
    override fun animators(view: View): Array<Animator> {
        val animator = ObjectAnimator.ofFloat(view, "translationY", view.measuredHeight.toFloat(), 0f)
        animator.duration = 400L
        animator.interpolator = DecelerateInterpolator(1.3f)
        return arrayOf(animator)
    }
}