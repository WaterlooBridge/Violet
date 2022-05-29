package com.zhenl.violet.widget

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.annotation.Nullable
import com.zhenl.violet.R

/**
 * Created by lin on 22-5-29.
 */
class RoundDrawable : GradientDrawable() {

    private var mRadiusAdjustBounds = true
    private var mFillColors: ColorStateList? = null
    private var mStrokeWidth = 0
    private var mStrokeColors: ColorStateList? = null

    fun setBgData(@Nullable colors: ColorStateList?) {
        mFillColors = colors
        val currentColor = colors?.getColorForState(state, 0) ?: Color.TRANSPARENT
        setColor(currentColor)
    }

    fun setStrokeData(width: Int, @Nullable colors: ColorStateList?) {
        mStrokeWidth = width
        mStrokeColors = colors
        val currentColor = colors?.getColorForState(state, 0) ?: Color.TRANSPARENT
        setStroke(width, currentColor)
    }

    fun setIsRadiusAdjustBounds(isRadiusAdjustBounds: Boolean) {
        mRadiusAdjustBounds = isRadiusAdjustBounds
    }

    override fun onStateChange(stateSet: IntArray?): Boolean {
        var superRet = super.onStateChange(stateSet)
        mFillColors?.let {
            val color = it.getColorForState(stateSet, 0)
            setColor(color)
            superRet = true
        }
        mStrokeColors?.let {
            val color = it.getColorForState(stateSet, 0)
            setStroke(mStrokeWidth, color)
            superRet = true
        }
        return superRet
    }

    override fun isStateful(): Boolean {
        return (mFillColors != null && mFillColors!!.isStateful
                || mStrokeColors != null && mStrokeColors!!.isStateful
                || super.isStateful())
    }

    override fun onBoundsChange(r: Rect) {
        super.onBoundsChange(r)
        if (mRadiusAdjustBounds) {
            cornerRadius = r.width().coerceAtMost(r.height()) / 2f
        }
    }

    companion object {

        fun fromAttributeSet(context: Context, attrs: AttributeSet?): RoundDrawable {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.RoundWidget)
            val colorBg = typedArray.getColorStateList(R.styleable.RoundWidget_rw_backgroundColor)
            val colorBorder = typedArray.getColorStateList(R.styleable.RoundWidget_rw_borderColor)
            val borderWidth =
                typedArray.getDimensionPixelSize(R.styleable.RoundWidget_rw_borderWidth, 0)
            val isRadiusAdjustBounds =
                typedArray.getBoolean(R.styleable.RoundWidget_rw_isRadiusAdjustBounds, false)
            val mRadius =
                typedArray.getDimensionPixelSize(R.styleable.RoundWidget_rw_radius, 0)
            val mRadiusTopLeft =
                typedArray.getDimensionPixelSize(R.styleable.RoundWidget_rw_radiusTopLeft, 0)
            val mRadiusTopRight =
                typedArray.getDimensionPixelSize(R.styleable.RoundWidget_rw_radiusTopRight, 0)
            val mRadiusBottomLeft =
                typedArray.getDimensionPixelSize(R.styleable.RoundWidget_rw_radiusBottomLeft, 0)
            val mRadiusBottomRight =
                typedArray.getDimensionPixelSize(R.styleable.RoundWidget_rw_radiusBottomRight, 0)
            typedArray.recycle()
            val bg = RoundDrawable()
            bg.setBgData(colorBg)
            if (borderWidth > 0)
                bg.setStrokeData(borderWidth, colorBorder)
            bg.setIsRadiusAdjustBounds(isRadiusAdjustBounds)
            if (mRadiusTopLeft > 0 || mRadiusTopRight > 0 || mRadiusBottomLeft > 0 || mRadiusBottomRight > 0) {
                val radii = floatArrayOf(
                    mRadiusTopLeft.toFloat(), mRadiusTopLeft.toFloat(),
                    mRadiusTopRight.toFloat(), mRadiusTopRight.toFloat(),
                    mRadiusBottomRight.toFloat(), mRadiusBottomRight.toFloat(),
                    mRadiusBottomLeft.toFloat(), mRadiusBottomLeft.toFloat()
                )
                bg.cornerRadii = radii
            } else if (mRadius > 0) {
                bg.cornerRadius = mRadius.toFloat()
            }
            return bg
        }
    }
}