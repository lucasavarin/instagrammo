package thushyanthan.scott.javalynx.instagrammo.fragments.secondaryFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_second.*
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPictures

class AddFragmentSecond private constructor(val randomPic: RandomPictures): Fragment(){
    companion object {
        fun makeInstance(randomPic: RandomPictures): AddFragmentSecond {
            return AddFragmentSecond(randomPic)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_second,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Picasso.get().load(randomPic.downloadUrl).into(picFullScreen)
        nextButton.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, AddFragmentThird.makeInstance(randomPic))?.commit()
        }
    }

}