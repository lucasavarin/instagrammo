package thushyanthan.scott.javalynx.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FollowerPayload (
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("name")
    val name:String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("picture")
    val picture : String
)