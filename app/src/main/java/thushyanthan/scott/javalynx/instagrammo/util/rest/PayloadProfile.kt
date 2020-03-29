package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PayloadProfile (
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
    val picture: String,
    @Expose
    @SerializedName("followersNumber")
    var followersNumber: String,
    @Expose
    @SerializedName("postNumber")
    var postNumber: String
)