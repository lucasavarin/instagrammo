package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostsProfileWrapper(
    @Expose
    @SerializedName(value = "profileId") val profileId: Int,
    @Expose
    @SerializedName(value = "name") val name: String,
    @Expose
    @SerializedName(value = "description") val description: String,
    @Expose
    @SerializedName(value = "picture") val picture: String,
    @Expose
    @SerializedName(value = "followersNumber") val followersNumber: Int,
    @Expose
    @SerializedName(value = "postNumber") val postNumber: Int
)