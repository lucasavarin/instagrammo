package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse (
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payload: List<PayloadProfile>,
    @Expose
    @SerializedName("count")
    val count: String
)