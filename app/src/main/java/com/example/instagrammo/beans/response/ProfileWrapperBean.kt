package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfileWrapperBean(
    @Expose
    @SerializedName(value = "result") val result : Boolean,
    @Expose
    @SerializedName(value = "payload") val payload: List<ProfileDataBean>,
    @Expose
    @SerializedName(value = "count") val count : String
)