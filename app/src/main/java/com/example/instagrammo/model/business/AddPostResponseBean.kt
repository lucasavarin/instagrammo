package com.example.instagrammo.model.business

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddPostResponseBean(
    @Expose
    @SerializedName("id") val id:String,
    @Expose
    @SerializedName("author") val autore:String,
    @Expose
    @SerializedName("width") val w:String,
    @Expose
    @SerializedName("height") val url:String,
    @Expose
    @SerializedName("download_url") val dUrl:String
) {
}