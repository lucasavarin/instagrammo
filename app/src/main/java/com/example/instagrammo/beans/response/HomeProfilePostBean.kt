package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeProfilePostBean (
    @Expose
    @SerializedName(value = "profileId") val profileId : String,
    @Expose
    @SerializedName(value = "name") val name : String,
    @Expose
    @SerializedName(value = "description") val description : String,
    @Expose
    @SerializedName(value = "picture") val picture : String,
    @Expose
    @SerializedName(value = "followersNumber") val followersNumber : String,
    @Expose
    @SerializedName(value = "postNumber") val postNumber : String

)