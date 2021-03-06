package com.zhenl.violet.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.zhenl.violet.R;

/**
 * Created by lin on 2018/2/26.
 */

public class ShapeTextView extends AppCompatTextView {

    private int mCornerSize;
    private int mSolidColor;

    private Paint paint;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeTextView, defStyleAttr, 0);
        mCornerSize = a.getDimensionPixelSize(R.styleable.ShapeTextView_corner, 0);
        mSolidColor = a.getColor(R.styleable.ShapeTextView_solid, 0);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mSolidColor);
    }

    public int getCornerSize() {
        return mCornerSize;
    }

    public void setCornerSize(int size) {
        this.mCornerSize = size;
        postInvalidate();
    }

    public int getSolidColor() {
        return mSolidColor;
    }

    public void setSolidColor(int color) {
        this.mSolidColor = color;
        paint.setColor(mSolidColor);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, mCornerSize);
        path.quadTo(0, 0, mCornerSize, 0);
        path.lineTo(getMeasuredWidth() - mCornerSize, 0);
        path.quadTo(getMeasuredWidth(), 0, getMeasuredWidth(), mCornerSize);
        path.lineTo(getMeasuredWidth(), getMeasuredHeight() - mCornerSize);
        path.quadTo(getMeasuredWidth(), getMeasuredHeight(), getMeasuredWidth() - mCornerSize, getMeasuredHeight());
        path.lineTo(mCornerSize, getMeasuredHeight());
        path.quadTo(0, getMeasuredHeight(), 0, getMeasuredHeight() - mCornerSize);
        path.close();
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
