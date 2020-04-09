package com.example.instagrammo.beans.business

import com.example.instagrammo.beans.response.PostResponseREST

data class Post(
    val profileId:String?,
    val postId:String,
    val title:String,
    val picture:String,
    val uploadTime:String,
    val profile: Profile?,
    var like:Boolean?=false
){
    companion object Post{
        fun createBusinessBean(rest: PostResponseREST): com.example.instagrammo.beans.business.Post {
            return Post(
                rest.profileId,
                rest.postId,
                rest.title,
                rest.picture,
                rest.uploadTime,
                Profile.createBusinessBean(
                    rest.profile
                )
            )
        }
    }
}