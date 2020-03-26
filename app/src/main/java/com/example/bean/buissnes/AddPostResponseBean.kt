package com.example.bean.buissnes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddPostResponseBean(
    @Expose()
    @SerializedName(value = "id") val id : String,
    @Expose()
    @SerializedName(value = "author") val author : String,
    @Expose()
    @SerializedName(value = "width") val width : String,
    @Expose()
    @SerializedName(value = "height") val height : String,
    @Expose()
    @SerializedName(value = "url") var url : String,
    @Expose()
    @SerializedName(value = "download_url") var download_url : String
)