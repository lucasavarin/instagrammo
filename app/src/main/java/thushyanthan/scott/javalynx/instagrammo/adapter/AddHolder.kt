package thushyanthan.scott.javalynx.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_random_pics_list_item.view.*
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPictures

class AddHolder (private val view: View) : RecyclerView.ViewHolder(view) {
    private var callback : ((randomPic : RandomPictures)->Unit)? = null

    fun assebleRandomPicsGrid(randomPic: RandomPictures){
        Picasso.get().load(randomPic.changedUrl).into(view.picture)
        view.setOnClickListener{
            callback?.invoke(randomPic)
        }
    }

    fun setOnPicClickedListener(callback:(randomPic: RandomPictures)->Unit){
        this.callback = callback
    }
}