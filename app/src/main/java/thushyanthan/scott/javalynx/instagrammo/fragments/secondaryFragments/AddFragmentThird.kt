package thushyanthan.scott.javalynx.instagrammo.fragments.secondaryFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view_header.view.*
import kotlinx.android.synthetic.main.fragment_add_third.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.ApiClient
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.fragments.AddFragment
import thushyanthan.scott.javalynx.instagrammo.util.rest.AddPictureRequest
import thushyanthan.scott.javalynx.instagrammo.util.rest.OnlyResultResponse
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPictures
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.prefs
import thushyanthan.scott.javalynx.instagrammo.util.transformation.CircleTransformation

class AddFragmentThird private constructor(val picToPost: RandomPictures): Fragment() {
    companion object {
        fun makeInstance(picToPost: RandomPictures): AddFragmentThird {
            return AddFragmentThird(picToPost)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_third,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Picasso.get().load(picToPost.changedUrl).transform(CircleTransformation()).into(picToBePosted)
        var picData = AddPictureRequest(prefs.profiloUtente,"",picToPost.downloadUrl)
        confirmButton.setOnClickListener{
            picData.title = picDescription.text.toString()
            addNewPictures(picData)
        }

        toolbarEditProfile.backButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,
                AddFragmentSecond.makeInstance(picToPost)
            )?.commit()
        }

        toolbarEditProfile.toolbarTitle.text = "Upload Picture"
    }

    fun addNewPictures(picData: AddPictureRequest){
        val call = ApiClient.getClient.addPostsToProfile(picData)
        call.enqueue(object : Callback<OnlyResultResponse>{
            override fun onFailure(call: Call<OnlyResultResponse>, t: Throwable) {
                Toast.makeText(activity, "Error addNewPictures1", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<OnlyResultResponse>,
                response: Response<OnlyResultResponse>
            ) {
                if (response.isSuccessful){
                    val resultBody = response.body()!!
                    if (resultBody.result){
                        Toast.makeText(activity, "Post added", Toast.LENGTH_SHORT).show()
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.fragment_container, AddFragment())?.commit()
                    }else{
                        Toast.makeText(activity, "Post not added", Toast.LENGTH_SHORT).show()
                    }
                }else
                {
                    Toast.makeText(activity, "Error addNewPictures2", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }
}