package thushyanthan.scott.javalynx.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @Expose
    val result: Boolean,
    @Expose
    val authToken: String,
    @Expose
    val profileID: Int
)