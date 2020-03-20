package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddPostResponse(
    @Expose
    @SerializedName(value = "id") val id : String,
    @Expose
    @SerializedName(value = "author") val author : String,
    @Expose
    @SerializedName(value = "width") val width : String,
    @Expose
    @SerializedName(value = "height") val height : String,
    @Expose
    @SerializedName(value = "url") val url : String,
    @Expose
    @SerializedName(value = "download_url") var download_url : String
)