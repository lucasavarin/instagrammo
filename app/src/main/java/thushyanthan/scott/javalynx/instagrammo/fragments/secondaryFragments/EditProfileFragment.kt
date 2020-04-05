package thushyanthan.scott.javalynx.instagrammo.fragments.secondaryFragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_view_header.view.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.ApiClient
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.fragments.ProfileFragment
import thushyanthan.scott.javalynx.instagrammo.util.rest.EditProfileBody
import thushyanthan.scott.javalynx.instagrammo.util.rest.OnlyResultResponse
import thushyanthan.scott.javalynx.instagrammo.util.transformation.CircleTransformation

class EditProfileFragment private constructor(val profileId:String, val username:String, val description:String, val picture : String ): Fragment() {
    companion object{
        fun makeInstance(profileId: String, username: String, description: String, picture: String):EditProfileFragment{
            return EditProfileFragment(profileId,username,description, picture)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_edit_profile,container,false)




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val descEditable = Editable.Factory.getInstance().newEditable(description)
        val usernameEditable = Editable.Factory.getInstance().newEditable(username)
        val profIdEditable = Editable.Factory.getInstance().newEditable(profileId)
        Picasso.get().load(picture).transform(CircleTransformation()).into(profPic)
        editDescription.text = descEditable
        editUsername.text = usernameEditable
        editPicId.text = profIdEditable


        super.onActivityCreated(savedInstanceState)
        toolbarEditProfile.backButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,ProfileFragment())?.commit()
        }

        toolbarEditProfile.toolbarTitle.text = "Edit Profile"

        saveEditsButton.setOnClickListener {
            saveEdits()
        }


    }



    fun saveEdits(){

        val editsToSend = EditProfileBody(profileId,username, description, picture)
        var valueChanged = false

        if (!editDescription.text!!.equals(description)){
            editsToSend.description = editDescription.text.toString()
            valueChanged = true
        }


        if (!editPicId.text!!.equals(profileId)){
            editsToSend.profileId = editPicId.text.toString()
            valueChanged = true
        }

        if (!editUsername.text!!.equals(username)) {
            editsToSend.name = editUsername.text.toString()
            valueChanged = true
        }


        if(valueChanged){
            val callSaveEdits: Call<OnlyResultResponse> = ApiClient.getClient.saveProfileEdits(bodyProfilo = editsToSend)
            callSaveEdits.enqueue(object : Callback<OnlyResultResponse>{
                override fun onFailure(call: Call<OnlyResultResponse>, t: Throwable) {
                    Toast.makeText(activity, "Error1SaveEdits", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<OnlyResultResponse>,
                    response: Response<OnlyResultResponse>
                ) {
                    if (response.isSuccessful){
                        if (response.body()!!.result){
                            activity?.supportFragmentManager?.beginTransaction()?.remove(this@EditProfileFragment)?.commit()
                            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,ProfileFragment())?.commit()
                        }
                    }
                }
            })
        }


    }
}