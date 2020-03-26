package com.instagrammo.bean.buissnes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilePostBean(

    @Expose()
    @SerializedName(value = "profileId") val profileId: Int,
    @Expose()
    @SerializedName(value = "postId") val postId: Int,
    @Expose()
    @SerializedName(value = "title") val title: String,
    @Expose()
    @SerializedName(value = "picture") val picture: String,
    @Expose()
    @SerializedName(value = "uploadTime") val uploadTime: String,
    @Expose()
    @SerializedName(value = "profile") val profile: ProfilesWrapper
)
