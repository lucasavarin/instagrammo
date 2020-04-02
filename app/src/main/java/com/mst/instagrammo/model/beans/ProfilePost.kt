package com.mst.instagrammo.model.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilePost (
    @Expose
    @SerializedName("postId") val profileId : String,
    @Expose
    @SerializedName("title") val title : String,
    @Expose
    @SerializedName("picture") val picture : String,
    @Expose
    @SerializedName("uploadTime") val uploadTime : String
)