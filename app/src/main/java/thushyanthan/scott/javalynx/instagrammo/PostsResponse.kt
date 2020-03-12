package thushyanthan.scott.javalynx.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostsResponse (
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payload: List<PostPayload>

)