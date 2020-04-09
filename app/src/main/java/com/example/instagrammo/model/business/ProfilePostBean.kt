package com.example.instagrammo.model.business

import com.example.instagrammo.model.business.ProfilePost
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilePostBean (
    @Expose
    @SerializedName("result") val result : Boolean,
    @Expose
    @SerializedName("payload") val payload : List<ProfilePost>,
    @Expose
    @SerializedName("count") val count: String
)