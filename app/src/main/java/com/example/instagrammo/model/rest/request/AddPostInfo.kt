package com.example.instagrammo.model.rest.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddPostInfo (
    @SerializedName(value = "profileId") val profileId : String,
    @SerializedName(value = "title") val title : String,
    @SerializedName(value = "picture") val picture : String
)