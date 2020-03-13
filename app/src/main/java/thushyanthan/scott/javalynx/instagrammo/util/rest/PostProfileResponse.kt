package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostProfileResponse (
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payloadProfile: List<PayloadPostProfile>,
    @Expose
    @SerializedName("count")
    val count: String
)