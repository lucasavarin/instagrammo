package com.mst.instagrammo.model.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomePost (
    @Expose
    @SerializedName("profileId") val profileId : String?,
    @Expose
    @SerializedName("postId") val postId : String,
    @Expose
    @SerializedName("title") val title : String,
    @Expose
    @SerializedName("picture") val picture : String,
    @Expose
    @SerializedName("uploadTime") val uploadTime : String,
    @Expose
    @SerializedName("profile") val profile : Profile
)