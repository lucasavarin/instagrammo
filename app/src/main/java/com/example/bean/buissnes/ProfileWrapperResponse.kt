package com.example.bean.buissnes

import com.example.bean.buissnes.HomeProfilePostBean
import com.example.bean.buissnes.HomeUserResponseBean
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileWrapperResponse (
@Expose()
@SerializedName(value = "payload") val payloadProfile: List<HomeProfilePostBean>,
@Expose()
@SerializedName(value = "result") val result: Boolean,
@Expose()
@SerializedName(value = "count") val count: Int
)