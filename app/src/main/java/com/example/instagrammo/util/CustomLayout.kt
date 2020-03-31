package com.example.instagrammo.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CustomLayout(context: Context, attributeSet: AttributeSet): LinearLayoutCompat(context, attributeSet), Target {
//
//    override fun onMeasure(width: Int, height: Int) {
//        val widthMode = MeasureSpec.getMode(width)
//        val heightMode = MeasureSpec.getMode(height)
//
//        val widthSize = MeasureSpec.getSize(width)
//        val heightSize = MeasureSpec.getSize(height)
//
//        // If one of the measures is match_parent, use that one to determine the size.
//        // If not, use the default implementation of onMeasure.
//        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
//            setMeasuredDimension(widthSize, widthSize)
//        } else if (heightMode == MeasureSpec.EXACTLY && widthMode != MeasureSpec.EXACTLY) {
//            setMeasuredDimension(heightSize, heightSize)
//        } else {
//            super.onMeasure(width, height)
//        }
//    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        background = BitmapDrawable(context.resources, bitmap)
    }
}