package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.SerializedName

data class EditProfileBody (
    @SerializedName("profileId")
    val profileId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("picture")
    val picture: String
)