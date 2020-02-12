package com.mst.instagrammo.beans

import com.google.gson.annotations.SerializedName

data class AuthRequest (
    @SerializedName("user") val user: String = "mioUsername",
    @SerializedName("pswd") val pswd: String = "miaPassword"
)