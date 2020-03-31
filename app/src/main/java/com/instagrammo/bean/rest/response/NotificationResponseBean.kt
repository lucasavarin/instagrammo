package com.instagrammo.bean.rest.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotificationResponseBean (

    @Expose()
    @SerializedName(value = "result") val result: Boolean,
    @Expose()
    @SerializedName(value = "payload") val payload: Int

)
