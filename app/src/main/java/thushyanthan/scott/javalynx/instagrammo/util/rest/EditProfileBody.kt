package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.SerializedName

data class EditProfileBody (
    @SerializedName("profileId")
    var profileId: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("picture")
    var picture: String
)