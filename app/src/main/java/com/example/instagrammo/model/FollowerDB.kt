package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FollowerDB (
    val id : Int,
    val name : String,
    val description: String
)