package com.instagrammo.view.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.login.R
import kotlinx.android.synthetic.main.custom_view_layout.view.*


class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context, attrs, defStyle){

    init{

        LayoutInflater.from(context).inflate(R.layout.custom_view_layout,this,true)
        val attributeView = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomView,0,0)

        titleCustom.text = attributeView.getString(R.styleable.CustomView_title)
        backButton.background= attributeView.getDrawable(R.styleable.CustomView_button)

        attributeView.recycle()

    }




}