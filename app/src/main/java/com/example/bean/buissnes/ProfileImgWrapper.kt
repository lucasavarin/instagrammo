package com.example.bean.buissnes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileImgWrapper (
    @Expose()
    @SerializedName(value = "payload") val payloadProfile: List<ProfilePostBean>,
    @Expose()
    @SerializedName(value = "result") val result: Boolean,
    @Expose()
    @SerializedName(value = "count") val count: Int
)
