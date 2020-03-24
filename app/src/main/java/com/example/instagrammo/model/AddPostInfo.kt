package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddPostInfo (
    @Expose()
    @SerializedName(value = "profileId") val profileId : String,
    @Expose()
    @SerializedName(value = "title") val title : String,
    @Expose()
    @SerializedName(value = "picture") val picture : String
)