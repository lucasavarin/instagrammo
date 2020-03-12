package com.example.bean.buissnes

import com.example.bean.buissnes.HomePayloadPostBean
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeWrapperPostBean(
    @Expose()
    @SerializedName(value = "result") val result : Boolean,
    @Expose()
    @SerializedName(value = "payload") val payload: List<HomePayloadPostBean>
)