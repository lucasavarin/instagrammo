package thushyanthan.scott.javalynx.instagrammo.util.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RandomPicturesResponse (
    @Expose
    @SerializedName("id")
    val id:String,
    @Expose
    @SerializedName("author")
    val author:String,
    @Expose
    @SerializedName("width")
    val width: Number,
    @Expose
    @SerializedName("height")
    val height: Number,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("download_url")
    val download_url: String
)