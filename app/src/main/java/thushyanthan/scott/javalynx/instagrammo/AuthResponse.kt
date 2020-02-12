package thushyanthan.scott.javalynx.instagrammo

data class AuthResponse (
    @SerializedName("result")
    val result: Boolean,
    @SerializedName("authToken")
    val authToken: ,
    @SerializedName("profileId")
    val profileID: Int
)