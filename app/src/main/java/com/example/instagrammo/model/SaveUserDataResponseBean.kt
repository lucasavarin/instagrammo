package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SaveUserDataResponseBean(
    @Expose()
    @SerializedName("result") val result:Boolean)