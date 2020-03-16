package com.example.instagrammo.data_class

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SaveProfileBean(
    @Expose
    @SerializedName(value = "result") val result : Boolean
)