package thushyanthan.scott.javalynx.instagrammo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPictures
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPicturesResponse

class AddAdapter (val randomPics : List<RandomPictures>): RecyclerView.Adapter<AddHolder>() {

    private var  adapterClicked: ((RandomPictures)->Unit)? = null
    override fun onBindViewHolder(holder: AddHolder, position: Int) {
        holder.assebleRandomPicsGrid(randomPics[position])
        holder.setOnPicClickedListener {
            adapterClicked?.invoke(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddHolder {
        return AddHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.add_random_pics_list_item,parent, false)
        )
    }

    override fun getItemCount(): Int {
        return randomPics.size
    }

    fun adapterSetOnPicClickedListener(callback: (RandomPictures)->Unit){
        adapterClicked = callback
    }


}