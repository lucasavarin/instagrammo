package thushyanthan.scott.javalynx.instagrammo

data class DataRequest (
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)