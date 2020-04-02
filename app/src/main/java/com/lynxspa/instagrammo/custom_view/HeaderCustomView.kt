package com.lynxspa.instagrammo.custom_view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.lynxspa.instagrammo.R
import java.util.zip.Inflater


class HeaderCustomView : ConstraintLayout {
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs) {
        inflate(context, R.layout.custom_view_header, this)
    }


}