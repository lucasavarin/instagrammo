package com.mst.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mst.instagrammo.model.beans.Profile

data class ProfileResponse(
    @Expose
    @SerializedName("result") val result: Boolean,
    @Expose
    @SerializedName("payload") val payload: List<Profile>,
    @Expose
    @SerializedName("count") val count: Int
)