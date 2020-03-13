package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.SerializedName

data class DataRequest (
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)