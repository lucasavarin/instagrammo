package thushyanthan.scott.javalynx.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeRecyclerViewOrizzontale (
    @Expose
    val result: Boolean,
    @Expose
    val payload: Array<Payload>
)
data class Payload(
    @Expose
    val id: String,
    @Expose
    val name: String,
    @Expose
    val description: String,
    @Expose
    val picture: String
)