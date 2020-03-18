package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfilePutResponseREST (
    @Expose
    @SerializedName("result")
    val result: Boolean
)