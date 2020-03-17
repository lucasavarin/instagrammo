package com.example.instagrammo.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class SquareImageView(context: Context, attrs: AttributeSet): AppCompatImageView(context, attrs) {
    override fun onMeasure(width: Int, height: Int) {
        val widthMode = MeasureSpec.getMode(width)
        val heightMode = MeasureSpec.getMode(height)

        val widthSize = MeasureSpec.getSize(width)
        val heightSize = MeasureSpec.getSize(height)

        // If one of the measures is match_parent, use that one to determine the size.
        // If not, use the default implementation of onMeasure.
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, widthSize)
        } else if (heightMode == MeasureSpec.EXACTLY && widthMode != MeasureSpec.EXACTLY) {
            setMeasuredDimension(heightSize, heightSize)
        } else {
            super.onMeasure(width, height)
        }
    }
}