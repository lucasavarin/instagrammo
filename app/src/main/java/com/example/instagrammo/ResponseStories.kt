package com.example.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseStories(
    @Expose
    @SerializedName("result") val result :String,
    @Expose
    @SerializedName("payload") val payload : Array<Profilo>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResponseStories

        if (result != other.result) return false
        if (!payload.contentEquals(other.payload)) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = result.hashCode()
        result1 = 31 * result1 + payload.contentHashCode()
        return result1
    }
}