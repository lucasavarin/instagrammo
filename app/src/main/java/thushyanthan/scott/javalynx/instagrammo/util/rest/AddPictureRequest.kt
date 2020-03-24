package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.SerializedName

data class AddPictureRequest (
    @SerializedName("profileId")
    var profileId: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("picture")
    var picture: String

)