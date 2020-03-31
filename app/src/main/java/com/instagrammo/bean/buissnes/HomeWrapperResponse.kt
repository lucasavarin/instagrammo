package com.instagrammo.bean.buissnes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeWrapperResponse(
    @Expose()
    @SerializedName(value = "result") val result : Boolean,
    @Expose()
    @SerializedName(value = "payload") val payload: List<HomeUserResponseBean>
    )