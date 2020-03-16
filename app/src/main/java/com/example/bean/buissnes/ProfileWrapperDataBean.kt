package com.example.bean.buissnes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileWrapperDataBean(
    @Expose()
    @SerializedName(value = "result") val result : Boolean,
    @Expose()
    @SerializedName(value = "payload") val payload: List<ProfileDataBean>,
    @Expose()
    @SerializedName(value = "count") val count: String
)