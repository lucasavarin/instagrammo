package com.example.instagrammo.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class SquareImageView(context: Context, attrs: AttributeSet): AppCompatImageView(context, attrs) {
    override fun onMeasure(width: Int, height: Int) {
        super.onMeasure(width, height)
        val measuredWidth = measuredWidth
        val measuredHeight = measuredHeight
        if (measuredWidth > measuredHeight) {
            setMeasuredDimension(measuredHeight, measuredHeight)
        } else {
            setMeasuredDimension(measuredWidth, measuredWidth)
        }
    }
}