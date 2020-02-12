package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @Expose
    @SerializedName("result") val result: Boolean?,
    @Expose
    @SerializedName("authToken") val authToken: String?,
    @Expose
    @SerializedName("profileId") val profileId: String?
)

