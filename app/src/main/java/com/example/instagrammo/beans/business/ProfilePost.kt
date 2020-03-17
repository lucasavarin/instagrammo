package com.example.instagrammo.beans.business

import com.example.instagrammo.beans.response.ProfilePostResponseREST

data class ProfilePost(
    val postId:String,
    val title:String,
    val picture:String,
    val uploadTime:String
){
    companion object ProfilePost{
        fun createBusinessBean(rest: ProfilePostResponseREST): com.example.instagrammo.beans.business.ProfilePost{
            return ProfilePost(
                rest.postId,
                rest.title,
                rest.picture,
                rest.uploadTime
            )
        }
    }
}