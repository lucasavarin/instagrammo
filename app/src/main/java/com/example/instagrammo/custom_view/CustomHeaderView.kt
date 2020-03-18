package com.example.instagrammo.custom_view

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.custom_header_view.view.*

class CustomHeaderView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs){

    init {
        inflate(context, R.layout.custom_header_view, this)

            val attributes =
                context.obtainStyledAttributes(attrs,
                    R.styleable.CustomHeaderView, 0, 0)

            header_txt.text =
                attributes.getString(R.styleable.CustomHeaderView_title)
            back_btn.background =
                attributes.getDrawable(R.styleable.CustomHeaderView_button)

            back_btn.setOnClickListener {}
            attributes.recycle()
    }
}