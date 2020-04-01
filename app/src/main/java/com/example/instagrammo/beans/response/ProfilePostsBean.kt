package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilePostsBean(
    @Expose
    @SerializedName(value = "profileId") val profileId : String,
    @Expose
    @SerializedName(value = "postId") val postId : String,
    @Expose
    @SerializedName(value = "title") val title : String,
    @Expose
    @SerializedName(value = "uploadTime") val uploadTime : String,
    @Expose
    @SerializedName(value = "picture") val picture : String,
    @Expose
    @SerializedName(value = "profile") val profile : PostsProfileWrapper
)