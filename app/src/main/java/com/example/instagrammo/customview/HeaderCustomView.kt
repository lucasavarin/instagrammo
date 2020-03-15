package com.example.instagrammo.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.custom_view_layout.view.*

class HeaderCustomView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var func: ArrayList<() -> Unit> = arrayListOf()

    init {

        inflate(context, R.layout.custom_view_layout, this)
        val attributi =
            context.theme.obtainStyledAttributes(attrs, R.styleable.HeaderCustomView, 0, 0)
        btnCustom.background = attributi.getDrawable(R.styleable.HeaderCustomView_button)
        txtCustom.text = attributi.getString(R.styleable.HeaderCustomView_titolo)
        isClickable = true
        this.visibility = View.VISIBLE
        btnCustom.setOnClickListener { func.forEach { it.invoke() } }
        attributi.recycle()

    }

    fun setOnBackClickListener(funzione: () -> Unit) {
        this.func.add(funzione)
    }


}