package thushyanthan.scott.javalynx.instagrammo.util.customView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.custom_view_header.view.*
import thushyanthan.scott.javalynx.instagrammo.R

class HeaderCustomView : ConstraintLayout{
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs){
        inflate(context, R.layout.custom_view_header, this)
    }
}