package com.example.bean.buissnes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilesWrapper (
    @Expose()
    @SerializedName(value = "profileId") val profileId: Int,
    @Expose()
    @SerializedName(value = "name") val name: String,
    @Expose()
    @SerializedName(value = "description") val description: String,
    @Expose()
    @SerializedName(value = "picture") val picture: String,
    @Expose()
    @SerializedName(value = "followersNumber") val followersNumber: Int,
    @Expose()
    @SerializedName(value = "postsNumber") val postsNumber: Int
    )
