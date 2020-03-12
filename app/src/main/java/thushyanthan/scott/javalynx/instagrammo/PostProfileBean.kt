package thushyanthan.scott.javalynx.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostProfileBean (
    @Expose
    @SerializedName("profileId")
    val profileId: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("picture")
    val profilePicture: String,
    @Expose
    @SerializedName("followersNumber")
    val followersNumber: String,
    @Expose
    @SerializedName("postsNumber")
    val postsNumber: String

)