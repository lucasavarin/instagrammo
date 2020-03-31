package com.instagrammo.bean.buissnes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileUpdateResponse(
    @Expose()
    @SerializedName(value = "result") val result : Boolean
    )