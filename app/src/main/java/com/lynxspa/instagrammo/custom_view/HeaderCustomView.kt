package com.lynxspa.instagrammo.custom_view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.lynxspa.instagrammo.R
import kotlinx.android.synthetic.main.custom_view_header.view.*
import java.util.zip.Inflater


class HeaderCustomView : ConstraintLayout {
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs) {
        inflate(context, R.layout.custom_view_header, this)
        backButton.setOnClickListener {
            par?.invoke()
        }
    }
    var par: (() -> Unit)? = null
    fun setOnBackClickListener(par: () -> Unit){
        this.par = par

    }




}