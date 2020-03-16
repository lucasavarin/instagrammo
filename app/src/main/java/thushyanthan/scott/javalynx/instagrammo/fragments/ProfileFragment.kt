package thushyanthan.scott.javalynx.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import thushyanthan.scott.javalynx.instagrammo.ApiClient
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.util.rest.ProfileResponse
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.prefs
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.fragments.secondaryFragments.EditProfileFragment
import thushyanthan.scott.javalynx.instagrammo.util.rest.PayloadProfile


class ProfileFragment: Fragment() {

    var profile: List<PayloadProfile> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getSingleProfile()
        modifyButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,
                EditProfileFragment.makeInstance(profile[0].profileId, profile[0].name, profile[0].description,profile[0].picture))?.commit()
        }

    }

    fun getSingleProfile() {
        val singleProfileCall = ApiClient.getClient.getSingleProfile(prefs.profiloUtente)
        singleProfileCall.enqueue(object: Callback<ProfileResponse>{
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Toast.makeText(activity, "Error GetSingleProfile1",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if(response.isSuccessful){
                    val resultBody = response.body()!!
                    if(resultBody.result){
                        profile = resultBody.payload
                    }
                }else{
                    Toast.makeText(activity, "Error GetSingleProfile2",Toast.LENGTH_SHORT).show()
                }


            }
        })

    }
}