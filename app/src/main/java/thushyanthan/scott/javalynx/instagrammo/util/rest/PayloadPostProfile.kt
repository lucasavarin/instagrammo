package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PayloadPostProfile (
    @Expose
    @SerializedName ("postId")
    val postId: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("picture")
    val picture: String,
    @Expose
    @SerializedName("uploadTime")
    val uploadTime: String
)